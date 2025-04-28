package com.kellieer.alarmsmvvmapp.presentation.components.screens.showpinnedadmin

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showalertacceptedadmin.components.ShowAcceptedAdminTopBar
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showpinnedadmin.components.ShowPinnedAdminTopBar
import com.kellieer.alarmsmvvmapp.ui.theme.AlarmsMVVMAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShowPinnedAdminScreen(navController: NavHostController){
    Scaffold(
        topBar = { ShowPinnedAdminTopBar(navController) },
        content = { },
        bottomBar = {}
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "ShowPinnedAdminScreen Preview"
)
@Composable
fun ShowPinnedAdminScreen() {
    val navController = rememberNavController()
    AlarmsMVVMAppTheme {
        ShowPinnedAdminScreen(navController)
    }
}