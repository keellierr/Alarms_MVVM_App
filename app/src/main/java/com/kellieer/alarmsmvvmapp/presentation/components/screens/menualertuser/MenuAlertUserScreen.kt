package com.kellieer.alarmsmvvmapp.presentation.components.screens.menualertuser

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kellieer.alarmsmvvmapp.presentation.components.screens.menualertuser.components.MenUserButtonBar
import com.kellieer.alarmsmvvmapp.presentation.components.screens.menualertuser.components.MenUserContent
import com.kellieer.alarmsmvvmapp.presentation.components.screens.menualertuser.components.MenuTopBarMenu
import com.kellieer.alarmsmvvmapp.ui.theme.AlarmsMVVMAppTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuAlertUserScreen(navController: NavHostController){
    Scaffold(
        topBar = {MenuTopBarMenu(navController)},
        content = {MenUserContent()},
        bottomBar = {}
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "MenUserScreen Preview",
    heightDp = 2200,
    widthDp = 400
)
@Composable
fun MenUserScreenPreview() {
    AlarmsMVVMAppTheme {
        val navController = rememberNavController()
        MenuAlertUserScreen(navController = navController)
    }
}