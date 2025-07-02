package com.example.patiobalmax.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.patiobalmax.database.entity.Puesto

@Dao
interface PuestoDao {

    @Insert
    suspend fun insert(puesto: Puesto)

    @Update
    suspend fun update(placa1: String, placa2: String, puestoId: Int)

    @Query("UPDATE puestos SET placa1 = :placa1, placa2 = :placa2 WHERE id = :puestoId")
    suspend fun actualizarPatentes(placa1: String, placa2: String, puestoId: Int)

    @Query("SELECT * FROM puestos WHERE patioId = :patioId")
    suspend fun getByPatio(patioId: Int): List<Puesto>

    @Query("SELECT * FROM puestos WHERE id = :puestoId")
    suspend fun getById(puestoId: Int): Puesto?
}
