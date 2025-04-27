package com.kellieer.alarmsmvvmapp.presentation.components.screens.showalert

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showalert.components.ShowAlertContent
import com.kellieer.alarmsmvvmapp.ui.theme.AlarmsMVVMAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShowAlertScreen(navController: NavHostController){
    Scaffold(
        topBar = {},
        content = {ShowAlertContent()},
        bottomBar = {}
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "MenUserScreen Preview"
)
@Composable
fun MenUserScreenPreview() {
    AlarmsMVVMAppTheme {
        val navController = rememberNavController()
        ShowAlertScreen(navController = navController)
    }
}
