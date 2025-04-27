package com.kellieer.alarmsmvvmapp.presentation.components.screens.registeralert

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.kellieer.alarmsmvvmapp.presentation.components.screens.registeralert.components.RegisterAlertContent
import com.kellieer.alarmsmvvmapp.ui.theme.AlarmsMVVMAppTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterAlertScreen(navController: NavHostController){
    Scaffold(topBar = {},
        content = { RegisterAlertContent(navController) },
        bottomBar = {}
    )
}


@Preview(showBackground = true, showSystemUi = true, name = "RegisterAlertScreen Preview")
@Composable
fun RegisterAlertScreenPreview() {
    AlarmsMVVMAppTheme {
        val navController = rememberNavController()
        RegisterAlertScreen(navController = navController)
    }
}