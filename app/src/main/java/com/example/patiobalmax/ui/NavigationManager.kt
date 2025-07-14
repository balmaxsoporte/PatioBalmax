package com.example.patiobalmax.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

object NavigationManager {
    fun goToLogin(activity: AppCompatActivity) {
        activity.startActivity(Intent(activity, LoginActivity::class.java))
        activity.finish()
    }

    fun goToMain(activity: AppCompatActivity) {
        activity.startActivity(Intent(activity, MainActivity::class.java))
        activity.finish()
    }
}
