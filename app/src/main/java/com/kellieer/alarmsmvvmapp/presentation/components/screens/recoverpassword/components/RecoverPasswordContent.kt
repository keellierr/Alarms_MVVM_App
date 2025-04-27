package com.kellieer.alarmsmvvmapp.presentation.components.screens.recoverpassword.components

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.kellieer.alarmsmvvmapp.R
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultButton
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultTextField
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultTextFieldPassword
import com.kellieer.alarmsmvvmapp.presentation.components.screens.recoverpassword.RecoverPasswordViewModel

@Composable
fun RecoverPasswordContent(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        BoxHeader(useImage = false, backgroundColor = Color(0xFF2E1A47))
        CardForm(navController = navController)
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
fun CardForm(navController: NavController) {
    val context = LocalContext.current
    val viewModel: RecoverPasswordViewModel = viewModel()

    Card(
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp)
            .offset(y = (-140).dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD8A7D4))
    ) {
        Column(Modifier.padding(horizontal = 20.dp)) {

            /* ---------- Encabezado ---------- */
            Text(
                text       = "Recuperar contraseña:",
                modifier   = Modifier.padding(top = 20.dp),
                fontSize   = 20.sp,
                fontWeight = FontWeight.Bold,
                color      = Color.White
            )
            Text("Por favor ingresar campos:")
            Spacer(Modifier.height(16.dp))

            /* ---------- Email ---------- */
            DefaultTextField(
                modifier   = Modifier.fillMaxWidth(),
                value      = viewModel.email,
                onValueChange = {
                    viewModel.email = it
                    viewModel.validateEmail()
                },
                label     = "Email",
                icon      = Icons.Default.Email,
                keyboardType = KeyboardType.Email,
                errorMsg  = viewModel.emailError,
                labelColor = Color.White
            )

            Spacer(Modifier.height(10.dp))

            /* ---------- Contraseña actual ---------- */
            DefaultTextFieldPassword(
                modifier   = Modifier.fillMaxWidth(),
                value      = viewModel.currentPassword,
                onValueChange = {
                    viewModel.currentPassword = it
                    viewModel.validateCurrentPassword()
                },
                label     = "Contraseña Actual",
                icon      = Icons.Default.Lock,
                errorMsg  = viewModel.currentPasswordError,
                labelColor = Color.White
            )

            Spacer(Modifier.height(10.dp))

            /* ---------- Nueva contraseña ---------- */
            DefaultTextFieldPassword(
                modifier   = Modifier.fillMaxWidth(),
                value      = viewModel.newPassword,
                onValueChange = {
                    viewModel.newPassword = it
                    viewModel.validateNewPassword()
                    viewModel.validatePasswordConfirm()   // para mantener sincronía
                },
                label     = "Nueva Contraseña",
                icon      = Icons.Default.Lock,
                errorMsg  = viewModel.newPasswordError,
                labelColor = Color.White
            )

            Spacer(Modifier.height(10.dp))

            /* ---------- Confirmar contraseña ---------- */
            DefaultTextFieldPassword(
                modifier   = Modifier.fillMaxWidth(),
                value      = viewModel.passwordConfirm,
                onValueChange = {
                    viewModel.passwordConfirm = it
                    viewModel.validatePasswordConfirm()
                },
                label     = "Confirmar Contraseña",
                icon      = Icons.Default.Lock,
                errorMsg  = viewModel.passwordConfirmError,
                labelColor = Color.White
            )

            Spacer(Modifier.height(10.dp))

            /* ---------- Botón cambiar ---------- */
            DefaultButton(
                text     = "Cambiar contraseña",
                enabled  = viewModel.isFormValid,
                onClick  = {
                    if (viewModel.attemptChange(context)) {
                        navController.navigateUp()  // vuelve a la pantalla anterior
                    }
                }
            )
        }
    }
}