package com.example.patiobalmax.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "puestos",
        foreignKeys = @ForeignKey(entity = PatioEntity.class,
                parentColumns = "id",
                childColumns = "id_patio",
                onDelete = CASCADE))
public class PuestoEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "id_patio")
    public int idPatio;

    @ColumnInfo(name = "numero_puesto")
    public int numeroPuesto;

    @ColumnInfo(name = "tipo_vehiculo_lugar1")
    public String tipoVehiculoLugar1;

    @ColumnInfo(name = "patente_lugar1")
    public String patenteLugar1;

    @ColumnInfo(name = "tipo_vehiculo_lugar2")
    public String tipoVehiculoLugar2;

    @ColumnInfo(name = "patente_lugar2")
    public String patenteLugar2;

    @ColumnInfo(name = "estado")
    public String estado;

    public PuestoEntity(int idPatio, int numeroPuesto, String tipoVehiculoLugar1,
                        String patenteLugar1, String tipoVehiculoLugar2, String patenteLugar2,
                        String estado) {
        this.idPatio = idPatio;
        this.numeroPuesto = numeroPuesto;
        this.tipoVehiculoLugar1 = tipoVehiculoLugar1;
        this.patenteLugar1 = patenteLugar1;
        this.tipoVehiculoLugar2 = tipoVehiculoLugar2;
        this.patenteLugar2 = patenteLugar2;
        this.estado = estado;
    }
}
