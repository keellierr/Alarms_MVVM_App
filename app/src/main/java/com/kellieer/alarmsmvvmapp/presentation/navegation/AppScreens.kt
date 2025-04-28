package com.kellieer.alarmsmvvmapp.presentation.navegation

sealed class AppScreens(val route: String) {
    object LoginScreen : AppScreens("LoginScreen")
    object RegisterScreen : AppScreens("RegisterScreen")
    object RecoverPasswordScreen : AppScreens("RecoverPasswordScreen")
    object RegisterEventsScreen : AppScreens("RegisterEventsScreen")
    object RegisterAlertScreen : AppScreens("RegisterAlertScreen")
    object EditRegisterScreen : AppScreens("EditRegisterScreen")
    object MenuAdminScreen : AppScreens("MenuAdminScreen")
    object MenuAlertUserScreen : AppScreens("MenuAlertUserScreen")
    object EditAlertScreen : AppScreens("EditAlertScreen")
    object MenUserScreen : AppScreens("MenUserScreen")
    object  QuicklyWorldAlertScreen : AppScreens("QuicklyWorldAlertScreen")
    object  ShowAlertScreen : AppScreens("ShowAlertScreen")
    object  ShowSavedAlertScreen : AppScreens("ShowSavedAlertScreen")
    object  ShowRefuseAlertScreen : AppScreens("ShowRefuseAlertScreen")
    object  ShowRemoveAlertScreen : AppScreens("ShowRemoveAlertScreen")
    object ShowPendingResolvedAlertScreen : AppScreens ("ShowPendingResolvedAlertScreen")
    object  ShowAlertAcceptedAdminScreen : AppScreens("ShowAlertAcceptedAdminScreen")
    object  ShowPinnedAdminScreen : AppScreens("ShowPinnedAdminScreen")
    object  ShowMyAlertAdminScreen : AppScreens("ShowMyAlertAdminScreen")
}
