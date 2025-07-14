package com.example.patiobalmax.util

import android.content.Context
import android.net.Uri
import java.io.BufferedReader
import java.io.InputStream

object FileUtils {

    fun leerArchivoDesdeUri(context: Context, uri: Uri): List<String> {
        val lineas = mutableListOf<String>()
        try {
            val inputStream: InputStream = context.contentResolver.openInputStream(uri) ?: return lineas
            val bufferedReader = BufferedReader(inputStream.reader())
            var linea: String?

            while (bufferedReader.readLine().also { linea = it } != null) {
                linea?.let { lineas.add(it) }
            }

            inputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return lineas
    }

    fun procesarArchivoArrendatarios(lineas: List<String>): List<com.example.patiobalmax.model.ArchivoRegistro> {
        val registros = mutableListOf<com.example.patiobalmax.model.ArchivoRegistro>()

        for (linea in lineas) {
            val datos = linea.split(",")
            if (datos.size >= 8) {
                val patio = datos[0].trim().replace("Patio", "").toIntOrNull() ?: continue
                val puesto = datos[1].trim().replace("Puesto", "").toIntOrNull() ?: continue
                val detalleLugar1 = datos[2].trim()
                val patenteLugar1 = datos[3].trim().uppercase()
                val detalleLugar2 = datos[4].trim()
                val patenteLugar2 = datos[5].trim().uppercase()
                val nombreArrendatario = datos[6].trim()

                registros.add(
                    com.example.patiobalmax.model.ArchivoRegistro(
                        patio = patio,
                        puesto = puesto,
                        detalleLugar1 = detalleLugar1,
                        patenteLugar1 = patenteLugar1,
                        detalleLugar2 = detalleLugar2.takeIf { it.isNotEmpty() },
                        patenteLugar2 = patenteLugar2.takeIf { it.isNotEmpty() },
                        nombreArrendatario = nombreArrendatario.takeIf { it.isNotEmpty() }
                    )
                )
            }
        }

        return registros
    }

    fun procesarArchivoParticulares(lineas: List<String>): List<com.example.patiobalmax.model.ArchivoRegistro> {
        val registros = mutableListOf<com.example.patiobalmax.model.ArchivoRegistro>()

        for (linea in lineas) {
            val datos = linea.split(",")
            if (datos.size >= 7) {
                val patio = datos[0].trim().replace("Patio", "").toIntOrNull() ?: continue
                val puesto = datos[1].trim().replace("Puesto", "").toIntOrNull() ?: continue
                val detalleLugar1 = datos[2].trim()
                val patenteLugar1 = datos[3].trim().uppercase()
                val detalleLugar2 = datos[4].trim()
                val patenteLugar2 = datos[5].trim().uppercase()

                registros.add(
                    com.example.patiobalmax.model.ArchivoRegistro(
                        patio = patio,
                        puesto = puesto,
                        detalleLugar1 = detalleLugar1,
                        patenteLugar1 = patenteLugar1,
                        detalleLugar2 = detalleLugar2.takeIf { it.isNotEmpty() },
                        patenteLugar2 = patenteLugar2.takeIf { it.isNotEmpty() },
                        nombreArrendatario = null
                    )
                )
            }
        }

        return registros
    }
}
