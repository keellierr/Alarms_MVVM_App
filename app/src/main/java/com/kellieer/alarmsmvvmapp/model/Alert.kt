package com.kellieer.alarmsmvvmapp.model

import java.io.Serializable

data class Alert(
    var id: String = "",
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val address: String = "",
    val city: String = "",
    val type: AlertType = AlertType.DEFAULT,
    val imagUrl: String = "",
): Serializable