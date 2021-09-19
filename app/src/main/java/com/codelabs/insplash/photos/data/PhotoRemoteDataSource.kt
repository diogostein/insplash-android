package com.codelabs.insplash.photos.data

import com.codelabs.insplash.app.api.UnsplashApiService
import com.codelabs.insplash.app.api.responses.PhotoResponse
import com.codelabs.insplash.app.api.responses.UserResponse

interface PhotoRemoteDataSource {
    suspend fun getPhotos(page: Int, perPage: Int): List<PhotoResponse>
    suspend fun getPhoto(id: String): PhotoResponse
    suspend fun getUser(username: String): UserResponse
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

    override suspend fun getPhoto(id: String): PhotoResponse {
        try {
            return apiService.getPhoto(id)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getUser(username: String): UserResponse {
        try {
            return apiService.getUser(username)
        } catch (e: Exception) {
            throw e
        }
    }

}

