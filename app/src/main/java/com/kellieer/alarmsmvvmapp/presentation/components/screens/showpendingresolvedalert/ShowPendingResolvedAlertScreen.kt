package com.kellieer.alarmsmvvmapp.presentation.components.screens.showpendingresolvedalert

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kellieer.alarmsmvvmapp.presentation.components.screens.menuadmin.MenuAdminScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showpendingresolvedalert.components.ShowPendingResolvedButtonBar
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showpendingresolvedalert.components.ShowPendingResolvedContent
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showpendingresolvedalert.components.ShowPendingResolvedTopBar
import com.kellieer.alarmsmvvmapp.presentation.navegation.AppScreens

import com.kellieer.alarmsmvvmapp.ui.theme.AlarmsMVVMAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShowPendingResolvedAlertScreen(navController: NavHostController){
    Scaffold(
        topBar = { ShowPendingResolvedTopBar(navController) },
        content = { ShowPendingResolvedContent() },
        bottomBar = {  }
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "MenuAdminScreen Preview"
)

@Composable
fun AppScreens.ShowPendingResolvedAlertScreen(){
    AlarmsMVVMAppTheme {
        val navController = rememberNavController()
        ShowPendingResolvedAlertScreen(navController = navController)
    }
}
