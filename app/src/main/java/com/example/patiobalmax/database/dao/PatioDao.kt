package com.example.patiobalmax.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.patiobalmax.database.entity.Patio

@Dao
interface PatioDao {
    @Query("SELECT * FROM patio")
    suspend fun getAll(): List<Patio>
}
