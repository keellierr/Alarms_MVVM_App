package com.kellieer.alarmsmvvmapp.presentation.components.screens.menuser

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.kellieer.alarmsmvvmapp.presentation.components.screens.menuser.components.MenuTopBar
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.kellieer.alarmsmvvmapp.presentation.components.screens.menualertuser.components.MenUserButtonBar
import com.kellieer.alarmsmvvmapp.presentation.components.screens.menuser.components.MenUserContentt
import com.kellieer.alarmsmvvmapp.ui.theme.AlarmsMVVMAppTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenUserScreen(navController: NavHostController){
    Scaffold(
        topBar = {MenuTopBar(navController)},
        content = {MenUserContentt(navController)},
        bottomBar = {MenUserButtonBar(navController)}
    )
}

@Preview(showBackground = true, showSystemUi = true, name = "MenUserScreen Preview")
@Composable
fun MenUserScreenPreview() {
    AlarmsMVVMAppTheme {
        val navController = rememberNavController()
        MenUserScreen(navController = navController)
    }
}
