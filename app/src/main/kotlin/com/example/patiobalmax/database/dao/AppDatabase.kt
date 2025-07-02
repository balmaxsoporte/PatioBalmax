package com.example.patiobalmax.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.patiobalmax.database.dao.PuestoDao
import com.example.patiobalmax.database.dao.UsuarioDao
import com.example.patiobalmax.database.dao.PatioDao
import com.example.patiobalmax.database.entity.Puesto
import com.example.patiobalmax.database.entity.Usuario
import com.example.patiobalmax.database.entity.Patio

@Database(entities = [Usuario::class, Patio::class, Puesto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun patioDao(): PatioDao
    abstract fun puestoDao(): PuestoDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "patiobalmax_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
