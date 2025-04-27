package com.kellieer.alarmsmvvmapp.presentation.components.screens.login.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.kellieer.alarmsmvvmapp.R
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultButton
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultTextField
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultTextFieldPassword
import com.kellieer.alarmsmvvmapp.presentation.components.screens.login.LoginViewModel
import com.kellieer.alarmsmvvmapp.presentation.navegation.AppScreens

@Composable
fun LoginContent(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        BoxHeader(useImage = false, backgroundColor = Color(0xFF2E1A47))
        CardForm(navController)
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

@Composable
fun CardForm(navController: NavHostController) {
    val context = LocalContext.current
    val viewModel: LoginViewModel = viewModel()

    Card(
        modifier = Modifier
            .padding(start = 44.dp, end = 40.dp)
            .offset(y = (-88).dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFD8A7D4)
        )
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = stringResource(id = R.string.login),
                modifier = Modifier.padding(top = 10.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(text = "Por favor iniciar sesión:")

            Spacer(modifier = Modifier.height(16.dp))

            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = viewModel.email,
                onValueChange = {
                    viewModel.email = it
                    viewModel.validateFields()
                },
                label = stringResource(id = R.string.usernameLabel),
                icon = Icons.Default.AccountBox,
                keyboardType = KeyboardType.Email,
                errorMsg = viewModel.emailError,
                labelColor = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))

            DefaultTextFieldPassword(
                modifier = Modifier
                    .fillMaxWidth(),

                value = viewModel.password,
                onValueChange = {
                    viewModel.password = it
                    viewModel.validateFields()
                },
                label = stringResource(R.string.passwordLabel),
                icon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password,
                errorMsg = viewModel.passwordError,
                labelColor = Color.White
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Recuperar contraseña",
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { navController.navigate(route = AppScreens.RecoverPasswordScreen.route) },
                color = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))

            DefaultButton(
                text = "Iniciar Sesión",
                onClick = {
                    val rol = viewModel.attemptLogin(context)
                    when (rol) {
                        "admin" -> {
                            navController.navigate(AppScreens.MenuAdminScreen.route) {
                                popUpTo(AppScreens.LoginScreen.route) { inclusive = true }
                            }
                        }
                        "usuario" -> {
                            navController.navigate(AppScreens.MenUserScreen.route) {
                                popUpTo(AppScreens.LoginScreen.route) { inclusive = true }
                            }
                        }
                        else -> {
                            Toast.makeText(context, "Credenciales inválidas", Toast.LENGTH_LONG).show()
                        }
                    }
                },
                enabled = viewModel.isFormValid,
                color = Color(0xFF5E4B8B)
            )
        }
    }
}
