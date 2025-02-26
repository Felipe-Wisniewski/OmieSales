package com.wisnitech.omiesales.ui.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

fun Calendar.getCurrentDate(): String {
    return try {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale("pt", "BR")).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        simpleDateFormat.format(timeInMillis)
    } catch (e: Exception) {
        e.printStackTrace()
        "-"
    }
}

fun String.getFormattedDate(): String {
    return try {
        val oldDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale("pt", "BR")).parse(this)
        SimpleDateFormat("dd/MM/yyyy - HH:mm:ss", Locale("pt", "BR")).format(oldDate ?: "")
    } catch (e: Exception) {
        e.printStackTrace()
        this
    }
}

fun String.getDdMmYyyy(): String {
    return try {
        val oldDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale("pt", "BR")).parse(this)
        SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR")).format(oldDate ?: "")
    } catch (e: Exception) {
        e.printStackTrace()
        this
    }
}