package com.kellieer.alarmsmvvmapp.presentation.components.screens.register.components

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.kellieer.alarmsmvvmapp.R
import com.kellieer.alarmsmvvmapp.model.User
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultButton
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultDatePickerDocked
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultTextField
import com.kellieer.alarmsmvvmapp.presentation.components.screens.login.LoginViewModel
import com.kellieer.alarmsmvvmapp.presentation.components.screens.register.RegisterViewModel
import com.kellieer.alarmsmvvmapp.presentation.navegation.AppScreens
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun RegisterContent(navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxWidth().offset(y=-20.dp)
            .verticalScroll(rememberScrollState())
            .clip(RoundedCornerShape(bottomStart = 5.dp, bottomEnd = 5.dp)),
    ) {
        BoxHeader(useImage = false, backgroundColor = Color(0xFF2E1A47))
        CardForm(navController)
    }
}

@Composable
fun CardForm(navController: NavHostController) {
    val context = LocalContext.current
    val viewModel: RegisterViewModel = viewModel()

    Card(
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp)
            .offset(y = (-235).dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD8A7D4))
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "Registro",
                modifier = Modifier.padding(top = 20.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Por favor ingresar campos:")
            Spacer(modifier = Modifier.height(16.dp))

            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.nombre,
                onValueChange = { viewModel.nombre = it },
                label = "Nombre",
                icon = Icons.Default.Face,
                keyboardType = KeyboardType.Text,
                labelColor = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.ciudad,
                onValueChange = {
                    viewModel.ciudad = it
                    viewModel.validateFields()
                },
                label = "Ciudad",
                icon = Icons.Default.Place,
                keyboardType = KeyboardType.Text,
                errorMsg = viewModel.ciudadError,
                labelColor = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.direccion,
                onValueChange = {
                    viewModel.direccion = it
                    viewModel.validateFields()
                },
                label = "Dirección",
                icon = Icons.Default.LocationOn,
                keyboardType = KeyboardType.Text,
                errorMsg = viewModel.direccionError,
                labelColor = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.email,
                onValueChange = {
                    viewModel.email = it
                    viewModel.validateFields()
                },
                label = "Email",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email,
                errorMsg = viewModel.emailError,
                labelColor = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.password,
                onValueChange = {
                    viewModel.password = it
                    viewModel.validateFields()
                },
                label = "Contraseña",
                icon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password,
                errorMsg = viewModel.passwordError,
                labelColor = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.confirmarPassword,
                onValueChange = {
                    viewModel.confirmarPassword = it
                    viewModel.validateFields()
                },
                label = "Confirmar Contraseña",
                icon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password,
                errorMsg = viewModel.confirmarPasswordError,
                labelColor = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))
            DefaultButton(
                text = "Registrarse",
                onClick = {
                    Toast.makeText(context, "Usuario registrado correctamente", Toast.LENGTH_LONG).show()
                    navController.navigate(AppScreens.LoginScreen.route) {
                        popUpTo(AppScreens.RegisterScreen.route) { inclusive = true }
                    }
                },
                enabled = viewModel.isButtonEnabled,
                color = Color(0xFF5E4B8B)
            )
        }
    }
}



@Composable
fun BoxHeader(
    useImage: Boolean = true,
    backgroundColor: Color = Color(0xFF2E1A47)
) {
    Box(
        modifier = Modifier
            .height(360.dp)
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
