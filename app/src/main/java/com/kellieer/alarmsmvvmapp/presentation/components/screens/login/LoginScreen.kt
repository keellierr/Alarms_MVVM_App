package com.kellieer.alarmsmvvmapp.presentation.components.screens.login

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kellieer.alarmsmvvmapp.presentation.components.screens.login.components.LoginButtonBar
import com.kellieer.alarmsmvvmapp.presentation.components.screens.login.components.LoginContent
import com.kellieer.alarmsmvvmapp.ui.theme.AlarmsMVVMAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavHostController){
    Scaffold(
        topBar = {},
        content = { LoginContent(navController) },
        bottomBar = { LoginButtonBar(navController) }
    )
}

@Preview(showBackground = true, name = "LoginScreen Preview")
@Composable
fun GreetingPreview() {
    AlarmsMVVMAppTheme {
        val navController = rememberNavController()
        LoginScreen(navController = navController)
    }
}