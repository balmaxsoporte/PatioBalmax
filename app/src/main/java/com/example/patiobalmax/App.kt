package com.example.patiobalmax

import android.app.Application
import com.example.patiobalmax.database.AppDatabase

class App : Application() {

    // Instancia única de la base de datos
    val database by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        // Aquí puedes inicializar otros componentes globales si es necesario
    }
}
