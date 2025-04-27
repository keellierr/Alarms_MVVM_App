package com.kellieer.alarmsmvvmapp.presentation.components.screens.showmyalert

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showmyalert.components.ShowMyAlertContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShowMyAlertScreen(){
    Scaffold(
        topBar = {},
        content = { ShowMyAlertContent() },
        bottomBar = {}
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "ShowMyAlertScreen Preview"
)
@Composable
fun ShowMyAlertScreenPreview() {
    val navController = rememberNavController()
    ShowMyAlertScreen()
}
