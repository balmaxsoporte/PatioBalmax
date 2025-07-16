package com.example.patiobalmax.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "particulares")
public class ParticularEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "patio")
    public String patio;

    @ColumnInfo(name = "puesto")
    public String puesto;

    @ColumnInfo(name = "detalle_lugar1")
    public String detalleLugar1;

    @ColumnInfo(name = "patente_lugar1")
    public String patenteLugar1;

    @ColumnInfo(name = "detalle_lugar2")
    public String detalleLugar2;

    @ColumnInfo(name = "patente_lugar2")
    public String patenteLugar2;

    public ParticularEntity(String patio, String puesto, String detalleLugar1,
                            String patenteLugar1, String detalleLugar2, String patenteLugar2) {
        this.patio = patio;
        this.puesto = puesto;
        this.detalleLugar1 = detalleLugar1;
        this.patenteLugar1 = patenteLugar1;
        this.detalleLugar2 = detalleLugar2;
        this.patenteLugar2 = patenteLugar2;
    }
}
