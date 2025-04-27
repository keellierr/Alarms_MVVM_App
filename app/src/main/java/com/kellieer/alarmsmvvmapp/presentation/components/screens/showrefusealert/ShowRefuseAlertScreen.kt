package com.kellieer.alarmsmvvmapp.presentation.components.screens.showrefusealert

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showrefusealert.components.ShowRefuseTopBar
import com.kellieer.alarmsmvvmapp.ui.theme.AlarmsMVVMAppTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShowRefuseAlertScreen(navController: NavHostController){
    Scaffold(
        topBar = {ShowRefuseTopBar(navController)},
        content = {},
        bottomBar = {}
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "ShowRefuseAlertScreen Preview"
)
@Composable
fun ShowRefuseAlertScreenPreview() {
    val navController = rememberNavController()
    AlarmsMVVMAppTheme {
        ShowRefuseAlertScreen(navController)
    }
}