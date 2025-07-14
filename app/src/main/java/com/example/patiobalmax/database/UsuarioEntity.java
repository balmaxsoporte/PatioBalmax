package com.example.patiobalmax.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "usuarios")
public class UsuarioEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nombre_usuario")
    public String nombreUsuario;

    @ColumnInfo(name = "contrasena")
    public String contrasena;

    @ColumnInfo(name = "permiso_administrador")
    public boolean permisoAdministrador;

    @ColumnInfo(name = "permiso_editar_patentes")
    public boolean permisoEditarPatentes;

    @ColumnInfo(name = "permiso_validar_puestos")
    public boolean permisoValidarPuestos;

    public UsuarioEntity(String nombreUsuario, String contrasena,
                         boolean permisoAdministrador, boolean permisoEditarPatentes,
                         boolean permisoValidarPuestos) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.permisoAdministrador = permisoAdministrador;
        this.permisoEditarPatentes = permisoEditarPatentes;
        this.permisoValidarPuestos = permisoValidarPuestos;
    }
}
