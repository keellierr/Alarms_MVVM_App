package com.kellieer.alarmsmvvmapp.presentation.components.screens.showalertacceptedadmin

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showalertacceptedadmin.components.ShowAcceptedAdminTopBar
import com.kellieer.alarmsmvvmapp.ui.theme.AlarmsMVVMAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShowAlertAcceptedAdminScreen(navController: NavHostController){
    Scaffold(
        topBar = {  ShowAcceptedAdminTopBar(navController) },
        content = { },
        bottomBar = {}
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "ShowSavedAlertScreen Preview"
)
@Composable
fun ShowAlertAcceptedAdminScreen() {
    val navController = rememberNavController()
    AlarmsMVVMAppTheme {
        ShowAlertAcceptedAdminScreen(navController)
    }
}