package com.kellieer.alarmsmvvmapp.presentation.components.screens.recoverpassword

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kellieer.alarmsmvvmapp.presentation.components.screens.recoverpassword.components.RecoverPasswordContent
import com.kellieer.alarmsmvvmapp.ui.theme.AlarmsMVVMAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RecoverPasswordScreen(navController: NavController){
    Scaffold(
        topBar = {},
        content = { RecoverPasswordContent(navController)},
        bottomBar = {},
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "RecoverPasswordScreen Preview"
)
@Composable
fun RecoverPasswordScreenPreview() {
    AlarmsMVVMAppTheme {
        val navController = rememberNavController()
        RecoverPasswordScreen(navController = navController)
    }
}