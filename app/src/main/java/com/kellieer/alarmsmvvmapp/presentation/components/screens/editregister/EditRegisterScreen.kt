package com.kellieer.alarmsmvvmapp.presentation.components.screens.editregister

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kellieer.alarmsmvvmapp.presentation.components.screens.editregister.components.EditRegisterContent
import com.kellieer.alarmsmvvmapp.ui.theme.AlarmsMVVMAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditRegisterScreen(navController: NavController){
    Scaffold( topBar = {}, content = { EditRegisterContent(navController) }, bottomBar = {} )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "EditRegisterScreen Preview",
    heightDp = 2000,
    widthDp = 400
)
@Composable
fun EditRegisterScreenPreview() {
    AlarmsMVVMAppTheme {
        val navController = rememberNavController()
        EditRegisterScreen(navController = navController)
    }
}