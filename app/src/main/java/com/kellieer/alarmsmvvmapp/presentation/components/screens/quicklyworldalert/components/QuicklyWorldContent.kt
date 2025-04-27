package com.kellieer.alarmsmvvmapp.presentation.components.screens.quicklyworldalert.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import com.kellieer.alarmsmvvmapp.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.android.gms.location.LocationServices
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.ImageHolder
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.LocationPuck2D
import com.mapbox.maps.plugin.locationcomponent.location
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import com.mapbox.maps.plugin.PuckBearing


@Composable
fun QuicklyWorldContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SmallMapBoxView(
            modifier = Modifier
                .fillMaxSize() // Asegura que el mapa se expanda completamente
        )
    }
}

@Composable
fun SmallMapBoxView(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }

    val bearingImage = drawableToImageHolder(context, R.drawable.globee, size = 56)

    AndroidView(
        factory = { mapView },
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp))
    ) {
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS) {
            mapView.location.apply {
                locationPuck = LocationPuck2D(
                    bearingImage = bearingImage
                )
                enabled = true
                puckBearingEnabled = true
                puckBearing = PuckBearing.COURSE
            }

            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location ->
                        location?.let {
                            mapView.getMapboxMap().setCamera(
                                CameraOptions.Builder()
                                    .center(Point.fromLngLat(it.longitude, it.latitude))
                                    .zoom(15.0)
                                    .build()
                            )
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "No se pudo obtener la ubicación", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(context, "Permiso de ubicación no concedido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}



@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onStart()
                Lifecycle.Event.ON_PAUSE,
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                else -> {}
            }
        }

        lifecycle.addObserver(observer)

        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    return mapView
}


fun drawableToImageHolder(context: Context, drawableId: Int,  size: Int = 48): ImageHolder? {
    val drawable = ContextCompat.getDrawable(context, drawableId) ?: return null
    val bitmap = Bitmap.createBitmap(
        size, // ancho fijo
        size,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setTint(android.graphics.Color.RED)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return ImageHolder.from(bitmap)
}
