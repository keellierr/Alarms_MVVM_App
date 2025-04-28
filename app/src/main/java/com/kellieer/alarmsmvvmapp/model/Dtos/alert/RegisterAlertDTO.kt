package com.kellieer.alarmsmvvmapp.model.Dtos.alert

import com.kellieer.alarmsmvvmapp.model.AlertType

data class RegisterAlertDTO(
    val title: String,
    val category: AlertType,
    val description: String,
    val address: String,
    val latitude: String,
    val longitude: String,
    val imageUri: String
)