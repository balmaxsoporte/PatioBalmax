package com.example.patiobalmax.util;

import android.content.Context;
import android.util.Log;

import com.example.patiobalmax.database.ArrendatarioEntity;
import com.example.patiobalmax.database.ParticularEntity;
import com.example.patiobalmax.model.ArchivoRegistro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<ArchivoRegistro> leerArchivo(Context context, String nombreArchivo) {
        List<ArchivoRegistro> registros = new ArrayList<>();
        try {
            InputStream inputStream = context.getResources().openRawResource(context.getResources().getIdentifier(nombreArchivo, "raw", context.getPackageName()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(",");
                if (campos.length >= 7) {
                    String patio = campos[0].replace("\"", "");
                    String puesto = campos[1].replace("\"", "");
                    String detalleLugar1 = campos[2].replace("\"", "");
                    String patenteLugar1 = campos[3].replace("\"", "");
                    String detalleLugar2 = campos[4].replace("\"", "");
                    String patenteLugar2 = campos[5].replace("\"", "");
                    String nombreArrendatario = campos[6].replace("\"", "");

                    registros.add(new ArchivoRegistro(
                            patio,
                            puesto,
                            detalleLugar1,
                            patenteLugar1,
                            detalleLugar2,
                            patenteLugar2,
                            nombreArrendatario
                    ));
                }
            }
            reader.close();
        } catch (IOException e) {
            Log.e("FileUtils", "Error al leer el archivo: " + nombreArchivo, e);
        }
        return registros;
    }
}
