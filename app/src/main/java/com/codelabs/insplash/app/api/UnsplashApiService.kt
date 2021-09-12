package com.codelabs.insplash.app.api

import com.codelabs.insplash.app.api.responses.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApiService {

    @GET("photos")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("order_by") orderBy: String? = null
    ): List<PhotoResponse>

}