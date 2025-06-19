package com.example.patiobalmax.utils

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.LinearLayout
import androidx.core.widget.NestedScrollView
import android.view.LayoutInflater
import android.view.View

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
