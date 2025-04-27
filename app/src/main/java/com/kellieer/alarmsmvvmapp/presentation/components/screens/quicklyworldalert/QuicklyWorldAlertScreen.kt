package com.kellieer.alarmsmvvmapp.presentation.components.screens.quicklyworldalert

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.kellieer.alarmsmvvmapp.presentation.components.screens.quicklyworldalert.components.QuicklyWorldContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun QuicklyWorldAlertScreen(){
    Scaffold(
        topBar = {},
        content = {QuicklyWorldContent()},
        bottomBar = {}
    )
}
