package com.kellieer.alarmsmvvmapp.model.Dtos

import com.kellieer.alarmsmvvmapp.model.AlertType

data class RegisterAlertDTO(
    val title: String,
    val category: AlertType,
    val description: String,
    val latitude: String,
    val longitude: String,
    val imageUri: String
)
