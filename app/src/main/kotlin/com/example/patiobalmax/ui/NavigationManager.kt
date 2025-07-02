package com.example.patiobalmax.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.patiobalmax.LoginEstacionamiento
import com.example.patiobalmax.MapaEstacionamiento
import com.example.patiobalmax.PatioEstacionamiento
import com.example.patiobalmax.AdministrarUsuarios

object NavigationManager {

    fun goToLogin(activity: AppCompatActivity) {
        activity.startActivity(Intent(activity, LoginEstacionamiento::class.java))
        activity.finish()
    }

    fun goToMap(activity: AppCompatActivity) {
        activity.startActivity(Intent(activity, MapaEstacionamiento::class.java))
    }

    fun goToPatio(activity: AppCompatActivity, patioNumero: Int) {
        val intent = Intent(activity, PatioEstacionamiento::class.java).apply {
            putExtra("patio_numero", patioNumero)
        }
        activity.startActivity(intent)
    }

    fun goToAdminUsers(activity: AppCompatActivity) {
        activity.startActivity(Intent(activity, AdministrarUsuarios::class.java))
    }
}
