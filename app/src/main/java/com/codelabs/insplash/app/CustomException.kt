package com.codelabs.insplash.app

import com.codelabs.insplash.app.api.UnsplashApiError

open class CustomException(
    override val message: String? = null,
) : Exception() {
    class Server(val code: Int? = null, val apiError: UnsplashApiError? = null) : CustomException()
}