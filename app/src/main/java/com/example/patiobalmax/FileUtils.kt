package com.example.patiobalmax.utils

import android.content.Context

object FileUtils {
    fun readLinesFromAsset(context: Context, fileName: String): List<String> {
        return context.assets.open(fileName).bufferedReader().use { it.readLines() }
    }

    fun getLicensePlatesFromLines(lines: List<String>): Set<String> {
        return lines.map { line ->
            line.split("\"").filter { it.isNotBlank() }.firstOrNull()?.trim()
        }.filterNotNull().toSet()
    }
}
