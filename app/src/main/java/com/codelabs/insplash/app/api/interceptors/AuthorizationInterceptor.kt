package com.codelabs.insplash.app.api.interceptors

import com.codelabs.insplash.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        request.apply {
            addHeader("Accept-Version", "v1")
            addHeader("Authorization", "Client-ID ${BuildConfig.ACCESS_API_KEY}")
        }

        return chain.proceed(request.build())
    }
}