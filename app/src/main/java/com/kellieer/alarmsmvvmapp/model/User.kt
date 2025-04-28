package com.kellieer.alarmsmvvmapp.model

data class User(
    val id: String,
    val city: String,
    val address: String,
    val name: String,
    val email: String,
    val password: String,
    val role: Role,
    val alerts: List<Alert> = emptyList()
)
