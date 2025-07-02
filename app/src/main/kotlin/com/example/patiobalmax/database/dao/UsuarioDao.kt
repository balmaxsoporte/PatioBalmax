package com.example.patiobalmax.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.patiobalmax.database.entity.Usuario

@Dao
interface UsuarioDao {

    @Insert
    suspend fun insert(usuario: Usuario)

    @Update
    suspend fun update(usuario: Usuario)

    @Query("SELECT * FROM usuarios WHERE nombre_usuario = :nombre")
    suspend fun findByNombre(nombre: String): Usuario?

    @Query("SELECT * FROM usuarios")
    suspend fun getAll(): List<Usuario>
}
