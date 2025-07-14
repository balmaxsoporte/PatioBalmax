package com.example.patiobalmax.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ArrendatarioDao {

    @Insert
    void insert(ArrendatarioEntity arrendatario);

    @Update
    void update(ArrendatarioEntity arrendatario);

    @Delete
    void delete(ArrendatarioEntity arrendatario);

    @Query("SELECT * FROM arrendatarios")
    LiveData<List<ArrendatarioEntity>> getAll();
}
