package com.kellieer.alarmsmvvmapp.presentation.components.screens.registeralert.components

import android.content.Context
import android.location.Geocoder
import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kellieer.alarmsmvvmapp.R
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultButton
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultTextField
import androidx.compose.runtime.Composable
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
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ListItemDefaults
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kellieer.alarmsmvvmapp.mapper.Mapper
import com.kellieer.alarmsmvvmapp.model.AlertType
import com.kelliier.alarmsmvvmapp.presentation.components.screens.registeralert.RegisterAlertViewModel
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.rememberCoroutineScope
import coil.compose.rememberAsyncImagePainter
import com.kelliier.alarmsmvvmapp.data.remote.ImgurRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


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
    val coroutineScope = rememberCoroutineScope()
    val photoUri = remember { mutableStateOf<Uri?>(null) }
    var isUploading by remember { mutableStateOf(false) }

    var showMap by remember { mutableStateOf(false) }

    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            coroutineScope.launch {
                isUploading = true
                viewModel.imageUri = it.toString()

                val uploadedImageUrl = ImgurRepository.uploadImage(Uri.parse(viewModel.imageUri), context)

                isUploading = false

                uploadedImageUrl?.let { url ->
                    viewModel.imageUri = url
                    Log.d("ImgurUpload", "Imagen subida exitosamente: $url")
                } ?: run {
                    Log.e("ImgurUpload", "Error al subir imagen a Imgur")
                }
            }
        }
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && photoUri.value != null) {
            coroutineScope.launch {
                isUploading = true
                viewModel.imageUri = photoUri.value.toString()

                val uploadedImageUrl = ImgurRepository.uploadImage(Uri.parse(viewModel.imageUri), context)

                isUploading = false

                uploadedImageUrl?.let { url ->
                    viewModel.imageUri = url
                    Log.d("ImgurUpload", "Imagen subida exitosamente: $url")
                } ?: run {
                    Log.e("ImgurUpload", "Error al subir imagen a Imgur")
                }
            }
        }
    }
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
            CategoryListCard(
                selectedCategory = viewModel.category,
                onCategorySelected = { selectedCategory ->
                    viewModel.category = selectedCategory
                }
            )

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
                        .height(240.dp),
                    viewModel = viewModel
                )
            }

            Spacer(Modifier.height(10.dp))

            /* ---------- Imagen ---------- */
            Text(
                text = "Cargar imagen",
                style = MaterialTheme.typography.labelLarge,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { imageLauncher.launch("image/*") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5E4B8B),
                        contentColor = Color.White
                    )
                ) {
                    Text("Galería")
                }

                Button(
                    onClick = {
                        val uri = createImageUri(context)
                        photoUri.value = uri
                        cameraLauncher.launch(uri)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5E4B8B),
                        contentColor = Color.White
                    )
                ) {
                    Text("Cámara")
                }
            }

            if (viewModel.imageUri.isNotEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))

                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .align(Alignment.CenterHorizontally)
                        .wrapContentHeight(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEAD9FF))
                ) {
                    androidx.compose.foundation.Image(
                        painter = rememberAsyncImagePainter(viewModel.imageUri),
                        contentDescription = "Imagen Adjunta",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                }
            }

            Spacer(Modifier.height(10.dp))

            /* ---------- Botón Registrar ---------- */
            DefaultButton(
                text = if (isUploading) "Subiendo..." else "Registrar",
                enabled = viewModel.isFormValid && !isUploading,
                color = Color(0xFF5E4B8B),
                onClick = {
                    coroutineScope.launch {
                        if (viewModel.attemptRegister(context)) {


                            val registerAlertDTO = Mapper.toRegisterAlertDTO(viewModel)
                            Log.d("RegisterAlertDTO", "Datos capturados: $registerAlertDTO")

                            withContext(Dispatchers.Main) {
                                navController.navigateUp()
                            }
                        }
                    }
                }
            )
            if (isUploading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF2E1A47)),
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .wrapContentHeight()
                            .offset(y = (-10).dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(24.dp)
                        ) {
                            LinearProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(6.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                color = Color(0xFFEAD9FF),
                                trackColor = Color(0xFF4C3B6E)
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "Subiendo imagen...",
                                color = Color.White,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

fun createImageUri(context: Context): Uri? {
    val contentResolver = context.contentResolver
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, "temp_image_${System.currentTimeMillis()}.jpg")
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
    }
    return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
}


@Composable
fun CategoryListCard(
    selectedCategory: AlertType,
    onCategorySelected: (AlertType) -> Unit
) {
    val cardColor = if (isSystemInDarkTheme()) Color(0xFF4C3B6E) else Color(0xFFF5EBFF)
    val textColor = if (isSystemInDarkTheme()) Color(0xFFEAD9FF) else Color(0xFF3E3065)

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Categorías", style = MaterialTheme.typography.titleMedium, color = textColor)
            }

            Spacer(modifier = Modifier.height(12.dp))

            CategoryItem(text = "Seguridad", selected = selectedCategory == AlertType.SECURITY) {
                onCategorySelected(AlertType.SECURITY)
            }
            Divider()
            CategoryItem(text = "Emergencias médicas", selected = selectedCategory == AlertType.MEDICAL_EMERGENCY) {
                onCategorySelected(AlertType.MEDICAL_EMERGENCY)
            }
            Divider()
            CategoryItem(text = "Infraestructura", selected = selectedCategory == AlertType.INFRASTRUCTURE) {
                onCategorySelected(AlertType.INFRASTRUCTURE)
            }
            Divider()
            CategoryItem(text = "Comunidad", selected = selectedCategory == AlertType.COMMUNITY) {
                onCategorySelected(AlertType.COMMUNITY)
            }
        }
    }
}



@Composable
fun CategoryItem(text: String, selected: Boolean, onClick: () -> Unit) {
    val isDark = isSystemInDarkTheme()
    val bubbleColor = if (isDark) Color(0xFF9C7FC1) else Color(0xFFEAD9FF)
    val textColor = if (isDark) Color.White else Color.Black
    val selectedBackground = if (selected) Color(0xFFB39DDB) else Color.Transparent

    Surface(
        color = selectedBackground,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() }
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
fun RealTimeLocationMapView(
    modifier: Modifier = Modifier,
    viewModel: RegisterAlertViewModel = viewModel() // 👈 Pasamos ViewModel aquí también
) {
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
                        if (location != null) {
                            viewModel.latitude = location.latitude.toString()
                            viewModel.longitude = location.longitude.toString()

                            mapView.getMapboxMap().setCamera(
                                CameraOptions.Builder()
                                    .center(Point.fromLngLat(location.longitude, location.latitude))
                                    .zoom(15.0)
                                    .build()
                            )
                        } else {
                            Toast.makeText(context, "Ubicación no disponible en este momento", Toast.LENGTH_SHORT).show()
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

@Composable
fun LinearDeterminateIndicator() {
    var currentProgress by remember { mutableStateOf(0f) }
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope() // Create a coroutine scope

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = {
            loading = true
            scope.launch {
                loadProgress { progress ->
                    currentProgress = progress
                }
                loading = false // Reset loading when the coroutine finishes
            }
        }, enabled = !loading) {
            Text("Start loading")
        }

        if (loading) {
            LinearProgressIndicator(
                progress = { currentProgress },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

/** Iterate the progress value */
suspend fun loadProgress(updateProgress: (Float) -> Unit) {
    for (i in 1..100) {
        updateProgress(i.toFloat() / 100)
        delay(100)
    }
}


