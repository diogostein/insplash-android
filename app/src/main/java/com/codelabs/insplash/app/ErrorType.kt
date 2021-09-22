package com.codelabs.insplash.app

import com.codelabs.insplash.InsplashApplication
import com.codelabs.insplash.R

sealed class ErrorType(customMessage: String? = null) {
    object Unknown : ErrorType()
    object NoConnection : ErrorType()
    object ApiRateLimitExceeded : ErrorType()
    class Failure(message: String? = null) : ErrorType(message)

    val message = customMessage ?: getDefaultMessage()

    private fun getDefaultMessage() = InsplashApplication.instance.getString(when (this) {
        is NoConnection -> R.string.no_internet_connection
        is ApiRateLimitExceeded -> R.string.api_rate_limit_exceeded
        is Failure -> R.string.failed_to_fetch_data
        is Unknown -> R.string.unknown_error
    })
}