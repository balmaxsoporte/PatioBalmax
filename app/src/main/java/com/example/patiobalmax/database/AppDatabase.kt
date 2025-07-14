package com.example.patiobalmax.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.patiobalmax.database.dao.PuestoDao
import com.example.patiobalmax.database.dao.UsuarioDao
import com.example.patiobalmax.database.entity.Puesto
import com.example.patiobalmax.database.entity.Usuario

@Database(entities = [Usuario::class, Puesto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun puestoDao(): PuestoDao

    companion object {
        // Nombre de la base de datos
        private const.val DATABASE_NAME = "patiobalmax.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}
