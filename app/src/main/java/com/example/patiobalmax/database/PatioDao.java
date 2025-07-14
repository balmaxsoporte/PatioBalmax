package com.example.patiobalmax.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PatioDao {

    @Insert
    void insert(PatioEntity patio);

    @Update
    void update(PatioEntity patio);

    @Delete
    void delete(PatioEntity patio);

    @Query("SELECT * FROM patios")
    LiveData<List<PatioEntity>> getAll();
}
