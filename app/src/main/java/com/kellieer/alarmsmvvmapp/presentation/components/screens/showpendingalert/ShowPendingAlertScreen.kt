package com.kellieer.alarmsmvvmapp.presentation.components.screens.showpendingalert

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kellieer.alarmsmvvmapp.presentation.components.screens.menuadmin.components.MenuAdminButtonBar
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showpendingalert.components.ShowAlertPendingContent
import com.kellieer.alarmsmvvmapp.ui.theme.AlarmsMVVMAppTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShowPendingAlertScreen(navController: NavHostController){
    Scaffold(
        topBar = {},
        content = {ShowAlertPendingContent()},
        bottomBar = {MenuAdminButtonBar(navController)}
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "ShowPendingAlertScreen Preview"
)
@Composable
fun ShowPendingAlertScreenPreview() {
    val navController = rememberNavController()
    AlarmsMVVMAppTheme {
        ShowPendingAlertScreen(navController = navController)
    }
}
