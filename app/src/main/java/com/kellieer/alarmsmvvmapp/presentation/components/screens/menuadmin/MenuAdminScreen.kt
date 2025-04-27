package com.kellieer.alarmsmvvmapp.presentation.components.screens.menuadmin

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kellieer.alarmsmvvmapp.presentation.components.screens.menuadmin.components.MenuAdminContent
import com.kellieer.alarmsmvvmapp.presentation.components.screens.menuadmin.components.MenuAdminButtonBar
import com.kellieer.alarmsmvvmapp.presentation.components.screens.menuadmin.components.MenuTopBarMenu
import com.kellieer.alarmsmvvmapp.ui.theme.AlarmsMVVMAppTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuAdminScreen(navController: NavHostController){
    Scaffold(
        topBar = {MenuTopBarMenu(navController)},
        content = {MenuAdminContent()},
        bottomBar = {MenuAdminButtonBar(navController)}
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "MenuAdminScreen Preview"
)
@Composable
fun MenuAdminScreenPreview() {
    AlarmsMVVMAppTheme {
        val navController = rememberNavController()
        MenuAdminScreen(navController = navController)
    }
}
