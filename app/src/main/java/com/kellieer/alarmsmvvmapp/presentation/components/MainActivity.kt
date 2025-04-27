package com.kellieer.alarmsmvvmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import android.Manifest
import com.kellieer.alarmsmvvmapp.controller.UserSessionManager
import com.kellieer.alarmsmvvmapp.presentation.components.screens.editregister.EditRegisterScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.menualertuser.MenuAlertUserScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.menuser.MenUserScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.quicklyworldalert.QuicklyWorldAlertScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.registeralert.RegisterAlertScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showalert.ShowAlertScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showrefusealert.ShowRefuseAlertScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showsavedalert.ShowSavedAlertScreen
import com.kellieer.alarmsmvvmapp.presentation.navegation.AppNavigation
import com.kellieer.alarmsmvvmapp.presentation.navegation.AppScreens
import com.kellieer.alarmsmvvmapp.ui.theme.AlarmsMVVMAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val modoPrueba = false
        setContent {
            AlarmsMVVMAppTheme {
                val navController = rememberNavController()
                var locationPermissionGranted by remember { mutableStateOf(false) }

                val permissionLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission(),
                    onResult = { granted ->
                        locationPermissionGranted = granted
                    }
                )

                LaunchedEffect(Unit) {
                    permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }
                if (locationPermissionGranted) {
                    if (modoPrueba) {
                        //EditRegisterScreen(navController = navController)
                        //RegisterScreen(navController = navController)
                        //LoginScreen(navController = navController)
                        //ShowAlertScreen(navController = navController)
                        //RegisterAlertScreen(navController = navController)
                        //QuicklyWorldAlertScreen()
                        //MenUserScreen(navController = navController)
                        //MenuAlertUserScreen(navController = navController)
                        //ShowSavedAlertScreen(navController = navController)
                        ShowRefuseAlertScreen(navController = navController)
//                        AppNavigation(
//                            navController = navController,
//                            startDestination = AppScreens.MenUserScreen.route
//                        )
                    } else {
                        val navController = rememberNavController()
                        val startDestination = AppScreens.LoginScreen.route
                        AppNavigation(
                            navController = navController,
                            startDestination = startDestination
                        )
                    }
                }
            }
        }
    }
}

