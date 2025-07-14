package com.example.patiobalmax.util;

public class Constants {

    // Nombres de archivos para carga de datos
    public static final String ARCHIVO_ARRENDATARIOS = "arrendatarios.txt";
    public static final String ARCHIVO_PARTICULARES = "particulares.txt";

    // Límites de historial de archivos
    public static final int MAX_ARCHIVOS_HISTORIAL = 10;
    public static final int MAX_ARCHIVOS_POR_TIPO = 5;

    // Tipos de vehículos
    public static final String[] TIPOS_VEHICULOS = {
            "Camión", "Camión 3/4", "Auto", "Camioneta", "Van", "Maquinaria Pesada"
    };

    // Tipos de rampas o segundo lugar
    public static final String[] TIPOS_RAMPA = {
            "Rampla", "Termo", "Cama Baja", "Container", "Tolva", "Estanque", "Carro"
    };

    // Estados de puestos
    public static final String ESTADO_LIBRE = "Libre";
    public static final String ESTADO_ARRENDATARIO = "Arrendatario";
    public static final String ESTADO_PARTICULAR = "Particular";

    // Permisos de usuarios
    public static final String PERMISO_ADMINISTRADOR = "Administrador";
    public static final String PERMISO_EDITAR_PATENTES = "Editar Patentes de Patios";
    public static final String PERMISO_VALIDAR_PUESTOS = "Validar Patios y Puestos";

    // Clave de usuario no editable
    public static final String USUARIO_ADMIN_NO_EDITABLE = "admin";

    // Directorios para almacenamiento de archivos (si fuera necesario)
    public static final String DIRECTORIO_ARCHIVOS = "/patiobalmax/archivos/";

    // Mensajes estándar
    public static final String MENSAJE_ARCHIVO_CARGADO_EXITOSAMENTE = "Archivo cargado exitosamente.";
    public static final String MENSAJE_ARCHIVO_NO_DISPONIBLE = "No se encontró el archivo.";
    public static final String MENSAJE_USUARIO_EXISTENTE = "El nombre de usuario ya existe.";
}
