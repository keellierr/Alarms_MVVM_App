package com.kellieer.alarmsmvvmapp.presentation.components.screens.editregister.components

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kellieer.alarmsmvvmapp.R
import com.kellieer.alarmsmvvmapp.model.User
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultButton
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultDatePickerDocked
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultTextField
import com.kellieer.alarmsmvvmapp.presentation.components.DefaultTextFieldPassword
import com.kellieer.alarmsmvvmapp.presentation.navegation.AppScreens
import androidx.compose.foundation.layout.size
import androidx.compose.ui.draw.shadow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kellieer.alarmsmvvmapp.presentation.components.screens.editregister.EditRegisterViewModel
import com.kellieer.alarmsmvvmapp.presentation.components.screens.register.RegisterViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun EditRegisterContent(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        BoxHeader(useImage = false, backgroundColor = Color(0xFF2E1A47))
        CardForm(navController = navController)
        CenteredCircularImage()
    }
}

@Composable
fun CenteredCircularImage() {
    Image(
        painter = painterResource(id = R.drawable.suave),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(160.dp)
            .offset(y = (-1156).dp, x = 118.dp)
            .clip(RoundedCornerShape(80.dp)).shadow(20.dp, CircleShape)

    )
}

@Composable
fun CardForm(navController: NavController) {
    val context = LocalContext.current
    val viewModel: EditRegisterViewModel = viewModel()

    Card(
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp)
            .offset(y = (-220).dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD8A7D4))
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Editar Perfil",
                modifier = Modifier
                    .padding(top = 20.dp)
                    .offset(x = 80.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.name,
                onValueChange = {
                    viewModel.name = it
                    viewModel.validate()
                },
                label = "Nombre",
                icon = Icons.Default.Edit,
                errorMsg = viewModel.errorName,
                labelColor = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))

            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.phoneNumber,
                onValueChange = {
                    viewModel.phoneNumber = it
                    viewModel.validate()
                },
                label = "Ciudad",
                icon = Icons.Default.Edit,
                errorMsg = viewModel.errorPhoneNumber,
                labelColor = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))

            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.address,
                onValueChange = {
                    viewModel.address = it
                    viewModel.validate()
                },
                label = "Dirección",
                icon = Icons.Default.Edit,
                errorMsg = viewModel.errorAddress,
                labelColor = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))

            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.email,
                onValueChange = {
                    viewModel.email = it
                    viewModel.validate()
                },
                label = "Email",
                icon = Icons.Default.Edit,
                errorMsg = viewModel.errorEmail,
                labelColor = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))

            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.password,
                onValueChange = {
                    viewModel.password = it
                    viewModel.validate()
                },
                label = "Contraseña",
                icon = Icons.Default.Edit,
                errorMsg = viewModel.errorPassword,
                labelColor = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))

            DefaultTextFieldPassword(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.password,
                onValueChange = {
                    viewModel.password = it
                    viewModel.validate()
                },
                label = "Confirmar Contraseña",
                icon = Icons.Default.Edit,
                errorMsg = viewModel.errorPassword,
                labelColor = Color.White
            )

            Spacer(modifier = Modifier.height(14.dp))

            DefaultButton(
                text = "Guardar Cambios",
                onClick = {
                    Toast.makeText(context, "Datos guardados correctamente", Toast.LENGTH_SHORT).show()
                    navController.navigate(AppScreens.MenuAlertUserScreen.route) {
                        popUpTo(AppScreens.EditRegisterScreen.route) { inclusive = true }
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
){
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
