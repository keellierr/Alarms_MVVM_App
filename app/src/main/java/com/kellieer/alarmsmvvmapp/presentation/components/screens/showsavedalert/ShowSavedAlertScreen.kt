package com.kellieer.alarmsmvvmapp.presentation.components.screens.showsavedalert

import android.annotation.SuppressLint
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showsavedalert.components.ShowSavedTopBar
import com.kellieer.alarmsmvvmapp.ui.theme.AlarmsMVVMAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShowSavedAlertScreen(navController: NavHostController){
    Scaffold(
        topBar = {  ShowSavedTopBar(navController) },
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
fun ShowSavedAlertScreenPreview() {
    val navController = rememberNavController()
    AlarmsMVVMAppTheme {
        ShowSavedAlertScreen(navController)
    }
}