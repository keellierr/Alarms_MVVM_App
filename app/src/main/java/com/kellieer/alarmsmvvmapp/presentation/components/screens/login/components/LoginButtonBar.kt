package com.kellieer.alarmsmvvmapp.presentation.components.screens.login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kellieer.alarmsmvvmapp.presentation.navegation.AppScreens

@Composable
fun LoginButtonBar(navController: NavHostController){
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¿Quieres crear tu propia cuenta?",
                modifier = Modifier.clickable { navController.navigate(route = AppScreens.RegisterScreen.route) })
        }
    }

}