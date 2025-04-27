package com.kellieer.alarmsmvvmapp.presentation.components.screens.registeralert.components

import android.content.Context
import android.location.Geocoder
import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kellieer.alarmsmvvmapp.R
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultButton
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultDatePickerDocked
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultDropdownMenu
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.google.android.gms.location.LocationServices
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.ImageHolder
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.LocationPuck2D
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.locationcomponent.location
import androidx.appcompat.content.res.AppCompatResources
import java.util.Locale
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ListItemDefaults
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kellieer.alarmsmvvmapp.presentation.components.screens.registeralert.RegisterAlertViewModel


@Composable
fun RegisterAlertContent(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        BoxHeader(useImage = false, backgroundColor = Color(0xFF2E1A47))
        CardForm(navController)
    }
}

@Composable
fun BoxHeader(
    useImage: Boolean = true,
    backgroundColor: Color = Color(0xFF2E1A47) // morado oscuro por defecto
) {
    Box(
        modifier = Modifier
            .height(460.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
            .background(if (!useImage) backgroundColor else Color.Transparent)
    ) {
        if (useImage) {
            Image(
                painter  = painterResource(id = R.drawable.mate),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun CardForm(navController: NavHostController) {
    val context = LocalContext.current
    val viewModel: RegisterAlertViewModel = viewModel()

    var showMap by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp, top = 26.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .offset(y = (-380).dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD8A7D4))
    ) {
        Column(Modifier.padding(horizontal = 20.dp)) {

            /* ---------- Encabezado ---------- */
            Text(
                text       = "Registrar Reporte",
                modifier   = Modifier.padding(top = 20.dp),
                fontSize   = 20.sp,
                fontWeight = FontWeight.Bold,
                color      = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text     = "Por favor ingresar los campos",
                style    = MaterialTheme.typography.labelLarge,
                color    = Color.White
            )
            Spacer(Modifier.height(10.dp))

            /* ---------- Título ---------- */
            DefaultTextField(
                modifier   = Modifier.fillMaxWidth(),
                value      = viewModel.title,
                onValueChange = {
                    viewModel.title = it
                    viewModel.validateFields()
                },
                label     = "Título",
                icon      = Icons.Default.Info,
                errorMsg  = viewModel.titleError,
                labelColor = Color.White
            )

            Spacer(Modifier.height(10.dp))

            /* ---------- Categoría ---------- */
            CategoryListCard(onEditClick = { /* … */ })

            Spacer(Modifier.height(10.dp))

            /* ---------- Descripción ---------- */
            DefaultTextField(
                modifier   = Modifier.fillMaxWidth(),
                value      = viewModel.description,
                onValueChange = {
                    viewModel.description = it
                    viewModel.validateFields()
                },
                label     = "Descripción",
                icon      = Icons.Default.Edit,
                errorMsg  = viewModel.descriptionError,
                labelColor = Color.White
            )

            Spacer(Modifier.height(10.dp))

            /* ---------- Dirección ---------- */
            DefaultTextField(
                modifier   = Modifier.fillMaxWidth(),
                value      = viewModel.address,
                onValueChange = {
                    viewModel.address = it
                    viewModel.validateFields()
                },
                label     = "Ubicación",
                icon      = Icons.Default.Edit,
                errorMsg  = viewModel.addressError,
                labelColor = Color.White
            )

            Spacer(Modifier.height(10.dp))

            /* ---------- Mapa ---------- */
            Text(
                text   = "Ubicación",
                style  = MaterialTheme.typography.labelLarge,
                color  = Color.White,
                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp),

            )

            Button(
                onClick = { showMap = !showMap },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5E4B8B),
                    contentColor   = Color.White
                )
            ) {
                Text(if (showMap) "Ocultar Mapa" else "Mostrar Mapa")
            }

            if (showMap) {
                Spacer(Modifier.height(10.dp))
                RealTimeLocationMapView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )
            }

            Spacer(Modifier.height(10.dp))

            /* ---------- Imagen ---------- */
            Text(
                text     = "Cargar imagen",
                style    = MaterialTheme.typography.labelLarge,
                color    = Color.White,
                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
            )

            Button(
                onClick  = { /* selector de imágenes */ },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(116.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                colors   = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5E4B8B),
                    contentColor   = Color.White
                )
            ) { Text("Escoger") }

            Spacer(Modifier.height(10.dp))

            /* ---------- Botón Registrar ---------- */
            DefaultButton(
                text     = "Registrar",
                enabled  = viewModel.isFormValid,
                color    = Color(0xFF5E4B8B),
                onClick  = {
                    if (viewModel.attemptRegister(context)) {
                        navController.navigateUp()
                    }
                }
            )
        }
    }
}


@Composable
fun CategoryListCard(
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit = {}
) {
    // Fondo lila claro
    val cardColor = if (isSystemInDarkTheme()) Color(0xFF4C3B6E) else Color(0xFFF5EBFF)
    val textColor = if (isSystemInDarkTheme()) Color(0xFFEAD9FF) else Color(0xFF3E3065)

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Categorías", style = MaterialTheme.typography.titleMedium, color = textColor)
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar",
                    tint = textColor,
                    modifier = Modifier.clickable { onEditClick() }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            CategoryItem(text = "Seguridad")
            Divider()
            CategoryItem(text = "Emergencias médicas")
            Divider()
            CategoryItem(text = "Infraestructura")
            Divider()
            CategoryItem(text = "Comunidad")
        }
    }
}

@Composable
fun CategoryItem(text: String) {
    val isDark = isSystemInDarkTheme()
    val bubbleColor = if (isDark) Color(0xFF9C7FC1) else Color(0xFFEAD9FF)
    val textColor = if (isDark) Color.White else Color.Black
    val itemBackground = if (isDark) Color(0xFFCE93D8) else Color(0xFFF8F4FF)

    Surface(
        color = itemBackground,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        ListItem(
            headlineContent = { Text(text, color = textColor) },
            leadingContent = {
                Surface(
                    shape = CircleShape,
                    color = bubbleColor
                ) {
                    Text(
                        text = text.first().uppercaseChar().toString(),
                        modifier = Modifier.padding(8.dp),
                        color = textColor,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            },
            colors = ListItemDefaults.colors(containerColor = Color.Transparent)
        )
    }
}



@Composable
fun SmallMapBoxView(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF2ECF7))
    ) {
        val context = LocalContext.current
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp)),
            factory = { ctx ->
                MapView(ctx).apply {
                    getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS)
                }
            }
        )
    }
}

@Composable
fun RealTimeLocationMapView(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    val bearingImage = drawableToImageHolder(context, R.drawable.globee)

    AndroidView(
        factory = { mapView },
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp)
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

            // Verificación de permisos antes de usar fusedLocationClient
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

fun drawableToImageHolder(context: Context, drawableId: Int): ImageHolder? {
    val drawable = ContextCompat.getDrawable(context, drawableId) ?: return null
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth.takeIf { it > 0 } ?: 64,
        drawable.intrinsicHeight.takeIf { it > 0 } ?: 64,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setTint(android.graphics.Color.RED)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return ImageHolder.from(bitmap)
}



