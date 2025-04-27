package com.kellieer.alarmsmvvmapp.presentation.components.screens.showresultalert

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import com.kellieer.alarmsmvvmapp.presentation.components.screens.showresultalert.components.ShowResultAlertContent
import com.kellieer.alarmsmvvmapp.presentation.components.screens.showresultalert.components.ShowResultTopBar
import com.kellieer.alarmsmvvmapp.ui.theme.AlarmsMVVMAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShowResultAlertScreen(){
    Scaffold(
        topBar = { ShowResultTopBar() },
        content = { ShowResultAlertContent() },
        bottomBar = {}
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "ShowResultAlertScreen Preview"
)
@Composable
fun ShowResultAlertScreenPreview() {
    AlarmsMVVMAppTheme {
        ShowResultAlertScreen()
    }
}
