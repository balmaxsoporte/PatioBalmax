package com.example.patiobalmax.util

import android.content.Context
import android.net.Uri
import androidx.core.content.ContextCompat
import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.Locale

object FileUtils {

    // Lee un archivo .txt desde recursos raw
    fun readRawFile(context: Context, resourceId: Int): String {
        return context.resources.openRawResource(resourceId).bufferedReader().use { it.readText() }
    }

    // Lee un archivo .txt desde almacenamiento interno/externo
    fun readFileFromUri(context: Context, uri: Uri): List<String> {
        val lineas = mutableListOf<String>()

        try {
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { reader ->
                    var linea: String?
                    while (reader.readLine().also { linea = it } != null) {
                        linea?.let {
                            if (it.isNotEmpty()) {
                                lineas.add(it)
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return lineas
    }

    // Obtiene el nombre del archivo desde su URI
    fun getFileNameFromUri(context: Context, uri: Uri): String {
        var fileName = "archivo.txt"

        context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (nameIndex > -1 && cursor.moveToFirst()) {
                fileName = cursor.getString(nameIndex)
            }
        }

        return fileName
    }

    // Valida si una línea tiene el formato correcto
    fun validarLinea(linea: String): Boolean {
        val datos = linea.split(",")
        return when {
            datos.size >= 7 && datos[0].startsWith("Patio") -> true
            datos.size == 2 -> true
            else -> false
        }
    }

    // Convierte una línea en objeto ArchivoRegistro
    fun parsearLineaAPatios(linea: String): ArchivoRegistro? {
        val datos = linea.split(",").map { it.trim() }

        if (datos.size >= 7) {
            return ArchivoRegistro(
                patio = datos[0],
                puesto = datos[1],
                detalleLugar1 = datos[2],
                patenteLugar1 = datos[3],
                detalleLugar2 = datos[4],
                patenteLugar2 = datos[5],
                nombreArrendatario = datos.getOrNull(6)
            )
        }

        return null
    }

    // Convierte una línea en objeto EstadoPuesto
    fun parsearLineaAPuestos(linea: String): EstadoPuesto? {
        val datos = linea.split(",").map { it.trim() }

        if (datos.size >= 7) {
            val numero = datos[1].filter { it.isDigit() }.toIntOrNull() ?: return null

            return EstadoPuesto(
                numero = numero,
                tipoLugar1 = datos[2],
                patenteLugar1 = datos[3].takeIf { it.isNotEmpty() },
                tipoLugar2 = datos[4].takeIf { it.isNotEmpty() },
                patenteLugar2 = datos[5].takeIf { it.isNotEmpty() },
                estado = if (datos[3].isNotEmpty()) "Arrendatario" else "Libre"
            )
        }

        return null
    }

    // Limita el historial a 5 archivos recientes por tipo
    fun mantenerHistorial(archivos: MutableList<ArchivoHistorial>, tipo: String): List<ArchivoHistorial> {
        val limite = if (tipo == "arrendatarios") 5 else 5

        archivos.sortByDescending { it.fecha }

        if (archivos.size > limite) {
            archivos.takeLast(archivos.size - limite)
        } else {
            archivos
        }

        return archivos
    }

    // Guarda registro del archivo cargado con fecha y tipo
    fun registrarArchivoCargado(nombre: String, tipo: String): ArchivoHistorial {
        return ArchivoHistorial(
            nombre = nombre,
            tipo = tipo,
            fecha = Date()
        )
    }

    // Formato de fecha actual para logs
    fun obtenerFechaActual(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return sdf.format(Date())
    }
}
