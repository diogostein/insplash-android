package com.codelabs.insplash.photos.data

import com.codelabs.insplash.app.api.UnsplashApiService
import com.codelabs.insplash.app.api.responses.PagingResponse
import com.codelabs.insplash.app.api.responses.PhotoResponse
import com.codelabs.insplash.app.api.responses.UserResponse
import com.codelabs.insplash.app.remoteResultHandler

interface PhotoRemoteDataSource {
    suspend fun getPhotos(page: Int, perPage: Int): List<PhotoResponse>
    suspend fun getPhoto(id: String): PhotoResponse
    suspend fun searchPhotos(query: String, page: Int, perPage: Int): PagingResponse<PhotoResponse>
    suspend fun getUser(username: String): UserResponse
}

class PhotoRemoteDataSourceImpl(
    private val apiService: UnsplashApiService,
) : PhotoRemoteDataSource {

    override suspend fun getPhotos(page: Int, perPage: Int): List<PhotoResponse> {
        return remoteResultHandler {
            apiService.getPhotos(page, perPage)
        }
    }

    override suspend fun getPhoto(id: String): PhotoResponse {
        return remoteResultHandler {
            apiService.getPhoto(id)
        }
    }

    override suspend fun searchPhotos(query: String, page: Int, perPage: Int): PagingResponse<PhotoResponse> {
        return remoteResultHandler {
            apiService.searchPhotos(query, page, perPage)
        }
    }

    override suspend fun getUser(username: String): UserResponse {
        return remoteResultHandler {
            apiService.getUser(username)
        }
    }

}

