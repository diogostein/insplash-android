package com.codelabs.insplash.app.api

import com.squareup.moshi.Moshi

object UnsplashApiErrorParser {
    fun parse(response: retrofit2.Response<*>): UnsplashApiError {
        return try {
            Moshi.Builder()
                .build()
                .adapter(UnsplashApiError::class.java)
                .fromJson(response.errorBody()!!.source())!!
        } catch (e: Exception) {
            e.printStackTrace()
            UnsplashApiError()
        }
    }
}
