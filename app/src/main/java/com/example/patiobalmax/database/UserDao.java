package com.example.patiobalmax.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(UsuarioEntity usuario);

    @Update
    void update(UsuarioEntity usuario);

    @Query("SELECT * FROM usuarios")
    LiveData<List<UsuarioEntity>> getAll();

    @Query("SELECT * FROM usuarios WHERE nombre_usuario = :username")
    UsuarioEntity getUsuarioByNombre(String username);
}
