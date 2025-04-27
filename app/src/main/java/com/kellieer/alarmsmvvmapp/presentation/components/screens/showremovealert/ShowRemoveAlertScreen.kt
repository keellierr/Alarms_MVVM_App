package com.kellieer.alarmsmvvmapp.presentation.components.screens.showremovealert

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showremovealert.components.ShowRemoveTopBar
import com.kellieer.alarmsmvvmapp.ui.theme.AlarmsMVVMAppTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShowRemoveAlertScreen(navController: NavHostController){
    Scaffold(
        topBar = { ShowRemoveTopBar(navController) },
        content = { },
        bottomBar = {}
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "ShowRemoveAlertScreen Preview"
)
@Composable
fun ShowRemoveAlertScreenPreview() {
    val navController = rememberNavController()
    AlarmsMVVMAppTheme {
        ShowRemoveAlertScreen(navController)
    }
}