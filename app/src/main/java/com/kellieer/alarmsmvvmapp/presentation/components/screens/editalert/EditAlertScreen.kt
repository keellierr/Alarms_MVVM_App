package com.kellieer.alarmsmvvmapp.presentation.components.screens.editalert

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kellieer.alarmsmvvmapp.presentation.components.screens.editalert.components.EditAlertContent
import com.kellieer.alarmsmvvmapp.ui.theme.AlarmsMVVMAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditAlertScreen(navController: NavHostController){
    Scaffold (topBar = {}, content = { EditAlertContent(navController) }, bottomBar = {} )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "EditAlertScreen Preview"
)

@Composable
fun EditAlertScreenPreview() {
    AlarmsMVVMAppTheme {
        val navController = rememberNavController()
        EditAlertScreen(navController = navController)
    }
}
