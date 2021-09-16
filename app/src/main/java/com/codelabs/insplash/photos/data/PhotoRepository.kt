package com.codelabs.insplash.photos.data

import com.codelabs.insplash.app.api.responses.toModel
import com.codelabs.insplash.app.models.Photo
import com.codelabs.insplash.app.states.RepositoryState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface PhotoRepository {
    fun getPhotos(page: Int, perPage: Int): Flow<RepositoryState<List<Photo>>>
}

class PhotoRepositoryImpl(private val remoteDataSource: PhotoRemoteDataSource) : PhotoRepository {

    override fun getPhotos(page: Int, perPage: Int): Flow<RepositoryState<List<Photo>>> {
        return flow {
            try {
                val result = remoteDataSource.getPhotos(page, perPage)

                emit(RepositoryState.Success(result.toModel()))
            } catch (e: Exception) {
                emit(RepositoryState.Failure(e.message))
            }
        }
    }

}