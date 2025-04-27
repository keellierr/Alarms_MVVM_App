package com.kellieer.alarmsmvvmapp.controller

import android.content.Context

object UserSessionManager {
    private const val PREF_NAME = "user_prefs"
    private const val KEY_ROLE = "user_role"

    fun guardarRol(context: Context, rol: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_ROLE, rol).apply()
    }

    fun obtenerRol(context: Context): String? {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_ROLE, null)
    }

    fun cerrarSesion(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
    }
}