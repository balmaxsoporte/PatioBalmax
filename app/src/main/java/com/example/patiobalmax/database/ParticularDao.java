package com.example.patiobalmax.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ParticularDao {

    @Insert
    void insert(ParticularEntity particular);

    @Update
    void update(ParticularEntity particular);

    @Delete
    void delete(ParticularEntity particular);

    @Query("SELECT * FROM particulares")
    LiveData<List<ParticularEntity>> getAll();
}
