package com.example.patiobalmax.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.patiobalmax.database.entity.Patio

@Dao
interface PatioDao {

    @Insert
    suspend fun insert(patio: Patio)

    @Query("SELECT * FROM patios")
    suspend fun getAll(): List<Patio>

    @Query("SELECT * FROM patios WHERE id = :patioId")
    suspend fun getById(patioId: Int): Patio?
}
