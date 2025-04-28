package com.kellieer.alarmsmvvmapp.presentation.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.kellieer.alarmsmvvmapp.presentation.components.screens.editalert.EditAlertScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.editregister.EditRegisterScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.login.LoginScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.menuadmin.MenuAdminScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.menualertuser.MenuAlertUserScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.menuser.MenUserScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.quicklyworldalert.QuicklyWorldAlertScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.recoverpassword.RecoverPasswordScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.register.RegisterScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.registeralert.RegisterAlertScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showalert.ShowAlertScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showalertacceptedadmin.ShowAlertAcceptedAdminScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showpendingresolvedalert.ShowPendingResolvedAlertScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showpinnedadmin.ShowPinnedAdminScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showrefusealert.ShowRefuseAlertScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showremovealert.ShowRemoveAlertScreen
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showsavedalert.ShowSavedAlertScreen

@Composable
fun AppNavigation(navController: NavHostController,startDestination: String) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = AppScreens.RegisterScreen.route) {
            RegisterScreen(navController)
        }
        composable(route = AppScreens.RecoverPasswordScreen.route) {
            RecoverPasswordScreen(navController)
        }
        composable(route = AppScreens.RegisterAlertScreen.route) {
            RegisterAlertScreen(navController)
        }
        composable(route = AppScreens.RegisterEventsScreen.route) {
            EditAlertScreen(navController)
        }
        composable(route = AppScreens.MenuAdminScreen.route) {
            MenuAdminScreen(navController)
        }
        composable(route = AppScreens.MenuAlertUserScreen.route) {
            MenuAlertUserScreen(navController)
        }
        composable(route = AppScreens.EditRegisterScreen.route) {
            EditRegisterScreen(navController)
        }
        composable(route = AppScreens.MenUserScreen.route) {
            MenUserScreen(navController)
        }
        composable(route = AppScreens.QuicklyWorldAlertScreen.route) {
            QuicklyWorldAlertScreen()
        }
        composable(route = AppScreens.ShowAlertScreen.route) {
            ShowAlertScreen(navController)
        }
        composable(route = AppScreens.ShowSavedAlertScreen.route) {
            ShowSavedAlertScreen(navController)
        }
        composable(route = AppScreens.ShowRefuseAlertScreen.route) {
            ShowRefuseAlertScreen(navController)
        }
        composable(route = AppScreens.ShowRemoveAlertScreen.route){
            ShowRemoveAlertScreen(navController)
        }
        composable(route = AppScreens.ShowPendingResolvedAlertScreen.route){
            ShowPendingResolvedAlertScreen(navController)
        }
        composable(route = AppScreens.ShowAlertAcceptedAdminScreen.route){
            ShowAlertAcceptedAdminScreen(navController)
        }
        composable(route = AppScreens.ShowPinnedAdminScreen.route){
            ShowPinnedAdminScreen(navController)
        }

    }
}

