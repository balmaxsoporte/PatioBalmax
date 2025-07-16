package com.example.patiobalmax.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "patios")
public class PatioEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nombre")
    public String nombre;

    public PatioEntity(String nombre) {
        this.nombre = nombre;
    }
}
