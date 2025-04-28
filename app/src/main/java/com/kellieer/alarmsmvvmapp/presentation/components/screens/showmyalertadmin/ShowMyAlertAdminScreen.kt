package com.kellieer.alarmsmvvmapp.presentation.components.screens.showmyalertadmin

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showmyalertadmin.components.ShowMyAlertAdminContent
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showmyalertadmin.components.ShowMyAlertTopBar
import com.kellieer.alarmsmvvmapp.ui.theme.AlarmsMVVMAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShowMyAlertAdminScreen(navController: NavHostController){
    Scaffold(
        topBar = {ShowMyAlertTopBar(navController)},
        content = {ShowMyAlertAdminContent()},
        bottomBar = {}
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "ShowMyAlertAdminScreen Preview"
)
@Composable
fun ShowMyAlertAdminScreen() {
    AlarmsMVVMAppTheme {
        val navController = rememberNavController()
        ShowMyAlertAdminScreen(navController = navController)
    }
}
