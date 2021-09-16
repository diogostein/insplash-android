package com.codelabs.insplash.photos.data

import com.codelabs.insplash.app.api.UnsplashApiService
import com.codelabs.insplash.app.api.responses.PhotoResponse

interface PhotoRemoteDataSource {
    suspend fun getPhotos(page: Int, perPage: Int): List<PhotoResponse>
}

class PhotoRemoteDataSourceImpl(
    private val apiService: UnsplashApiService,
) : PhotoRemoteDataSource {

    override suspend fun getPhotos(page: Int, perPage: Int): List<PhotoResponse> {
        try {
            return apiService.getPhotos(page, perPage)
        } catch (e: Exception) {
            throw e
        }
    }

}

