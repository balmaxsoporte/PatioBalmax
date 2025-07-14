package com.example.patiobalmax.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.patiobalmax.model.EstadoPuesto

@Dao
interface PuestoDao {

    @Query("SELECT * FROM puesto WHERE patioId = :patioId")
    suspend fun getPuestosByPatio(patioId: Int): List<EstadoPuesto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(puestos: List<EstadoPuesto>)
}
