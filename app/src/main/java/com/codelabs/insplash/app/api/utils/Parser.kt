package com.codelabs.insplash.app.api.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.toDateTime(pattern: String = "yyyy-MM-dd'T'HH:mm:ssZ"): Date? {
    return try {
        SimpleDateFormat(pattern, Locale.getDefault()).parse(this)
    } catch (e: ParseException) {
        e.printStackTrace()
        null
    }
}