package com.codelabs.insplash.photos.data

import com.codelabs.insplash.app.api.UnsplashApiService
import com.codelabs.insplash.app.api.responses.PhotoResponse
import com.codelabs.insplash.app.states.RepositoryState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface PhotoRepository {
    fun getPhotos(page: Int, perPage: Int): Flow<RepositoryState<List<PhotoResponse>>>
}

class PhotoRepositoryImpl(private val apiService: UnsplashApiService) : PhotoRepository {

    override fun getPhotos(page: Int, perPage: Int): Flow<RepositoryState<List<PhotoResponse>>> {
        return flow {
            try {
                val result = apiService.getPhotos(page, perPage)

                emit(RepositoryState.Success(result))
            } catch (e: Exception) {
                emit(RepositoryState.Failure(e.message))
            }
        }
    }

}