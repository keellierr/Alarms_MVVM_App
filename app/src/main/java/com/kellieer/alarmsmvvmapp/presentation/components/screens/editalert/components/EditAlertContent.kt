package com.kellieer.alarmsmvvmapp.presentation.components.screens.editalert.components

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.kellieer.alarmsmvvmapp.R
import com.kellieer.alarmsmvvmapp.model.Alert
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultButton
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultDatePickerDocked
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultTextField
import kotlinx.coroutines.launch
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kellieer.alarmsmvvmapp.presentation.components.screens.editalert.EditAlertViewModel

@Composable
fun EditAlertContent(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        BoxHeader()
        CardForm(navController)
    }
}

@Composable
fun BoxHeader() {
    Box(
        modifier = Modifier
            .height(460.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp)),
    ) {
        Image(
            painterResource(id = R.drawable.guadua),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun CardForm(navController: NavHostController) {
    val context          = LocalContext.current
    val viewModel: EditAlertViewModel = viewModel()   // ← inyección simple (sin Hilt)

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
                text       = "Editar Reporte",
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

            /* ---------- Campos ---------- */
            DefaultTextField(
                modifier   = Modifier.fillMaxWidth(),
                value      = viewModel.title,
                onValueChange = {
                    viewModel.title = it
                    viewModel.validateFields()
                },
                label     = "Título",
                icon      = Icons.Default.Info,
                errorMsg  = viewModel.titleError
            )

            Spacer(Modifier.height(10.dp))

            CategoryListCard(/* … */)

            Spacer(Modifier.height(10.dp))

            DefaultTextField(
                modifier   = Modifier.fillMaxWidth(),
                value      = viewModel.description,
                onValueChange = {
                    viewModel.description = it
                    viewModel.validateFields()
                },
                label     = "Descripción",
                icon      = Icons.Default.Edit,
                errorMsg  = viewModel.descriptionError
            )

            Spacer(Modifier.height(10.dp))

            DefaultTextField(
                modifier   = Modifier.fillMaxWidth(),
                value      = viewModel.address,
                onValueChange = {
                    viewModel.address = it
                    viewModel.validateFields()
                },
                label     = "Ubicación",
                icon      = Icons.Default.Edit,
                errorMsg  = viewModel.addressError
            )

            Spacer(Modifier.height(10.dp))

            /* ---------- Botón “Escoger imagen” ---------- */
            Button(
                onClick   = { /* selector de imágenes */ },
                modifier  = Modifier.align(Alignment.CenterHorizontally),
                colors    = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5E4B8B),
                    contentColor   = Color.White
                )
            ) { Text("Escoger") }

            Spacer(Modifier.height(10.dp))

            /* ---------- Botón “Editar” ---------- */
            DefaultButton(
                text     = "Editar",
                enabled  = viewModel.isFormValid,
                color    = Color(0xFF5E4B8B),
                onClick  = {
                    if (viewModel.attemptUpdate(context)) {
                        navController.navigateUp()   // vuelve a la pantalla anterior
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
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5EBFF)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Categorías", style = MaterialTheme.typography.titleMedium)
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar",
                    modifier = Modifier.clickable { onEditClick() }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            CategoryItem(text = "Seguridad")
            Divider()
            CategoryItem(text = "Emergencias médicas")
            Divider()
            CategoryItem(text = "Infraestructura")
        }
    }
}

@Composable
fun CategoryItem(text: String) {
    ListItem(
        headlineContent = { Text(text) },
        leadingContent = {
            Surface(
                shape = CircleShape,
                color = Color(0xFFEAD9FF)
            ) {
                Text(
                    text = text.first().uppercaseChar().toString(),
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    )
}