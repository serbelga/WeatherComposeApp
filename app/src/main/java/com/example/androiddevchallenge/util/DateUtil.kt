package com.example.androiddevchallenge.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    fun formatMillis(millis: Long): String {
        val date = Date(millis)
        val dateFormatter = SimpleDateFormat("HH:mm", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        return dateFormatter.format(date)
    }
}