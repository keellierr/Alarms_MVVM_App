package com.kellieer.alarmsmvvmapp.presentation.components.screens.register


import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kellieer.alarmsmvvmapp.presentation.components.screens.register.components.RegisterContent
import com.kellieer.alarmsmvvmapp.ui.theme.AlarmsMVVMAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(navController: NavHostController){
    Scaffold(
        topBar = { },
        content = {
            RegisterContent(navController)
        },
        bottomBar = {}
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "RegisterScreen Preview"
)
@Composable
fun RegisterScreenPreview() {
    AlarmsMVVMAppTheme {
        val navController = rememberNavController()
        RegisterScreen(navController = navController)
    }
}