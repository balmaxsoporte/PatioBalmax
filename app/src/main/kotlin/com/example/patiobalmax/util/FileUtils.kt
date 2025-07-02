package com.example.patiobalmax.util

import android.content.Context
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

object FileUtils {

    fun readRawFile(context: Context, resourceId: Int): List<String> {
        val inputStream = context.resources.openRawResource(resourceId)
        val reader = BufferedReader(InputStreamReader(inputStream))
        return reader.readLines()
    }

    fun saveToFile(context: Context, filename: String, content: String) {
        val file = File(context.filesDir, filename)
        file.writeText(content)
    }

    fun loadFromFile(context: Context, filename: String): String? {
        val file = File(context.filesDir, filename)
        return if (file.exists()) file.readText() else null
    }
}
