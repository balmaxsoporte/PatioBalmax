package com.example.patiobalmax.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PuestoDao {

    @Insert
    void insert(PuestoEntity puesto);

    @Update
    void update(PuestoEntity puesto);

    @Delete
    void delete(PuestoEntity puesto);

    @Query("SELECT * FROM puestos")
    LiveData<List<PuestoEntity>> getAll();

    @Query("SELECT * FROM puestos WHERE id_patio = :idPatio")
    LiveData<List<PuestoEntity>> getAllByPatioId(int idPatio);
}
