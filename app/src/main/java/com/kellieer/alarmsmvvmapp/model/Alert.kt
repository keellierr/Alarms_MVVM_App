package com.kellieer.alarmsmvvmapp.model


data class Alert(
    val id: String,
    val title: String,
    val category: AlertType,
    val description: String,
    val address: String,
    val latitude: String,
    val longitude: String,
    val imageUri: String
)