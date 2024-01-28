package com.flasshka.twoactivity.model

import android.content.Context
import android.icu.text.SimpleDateFormat
import timber.log.Timber
import java.io.File
import java.io.FileWriter
import java.util.Date
import java.util.Locale

class FileLoggingTree (private val context: Context): Timber.Tree() {
    private val logFileName = "app_logs.txt"
    private val dateTimePattern = "yyyy-MM-dd HH:mm:ss.SSS"

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val logFile = File(context.getExternalFilesDir(null), logFileName)

        val timestamp = SimpleDateFormat(dateTimePattern, Locale.getDefault())
            .format(Date())

        FileWriter(logFile, true).use { fileWriter ->
            fileWriter.append("$timestamp $tag/$priority: $message\n")
        }
    }
}