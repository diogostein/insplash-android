package com.codelabs.insplash.photos.data

import com.codelabs.insplash.app.ErrorType
import com.codelabs.insplash.app.api.responses.toModel
import com.codelabs.insplash.app.helpers.NetworkHelper
import com.codelabs.insplash.app.models.Photo
import com.codelabs.insplash.app.states.RepositoryState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface PhotoRepository {
    fun getPhotos(page: Int, perPage: Int): Flow<RepositoryState<List<Photo>>>
    fun getPhoto(id: String): Flow<RepositoryState<Photo>>
}

class PhotoRepositoryImpl(
    private val remoteDataSource: PhotoRemoteDataSource,
    private val networkHelper: NetworkHelper,
) : PhotoRepository {

    companion object {
        private val photosInMemory = mutableMapOf<String, Photo>()
    }

    override fun getPhotos(page: Int, perPage: Int) = flow {
        if (!networkHelper.isConnectionAvailable()) {
            emit(RepositoryState.Failure(ErrorType.NoConnection))
        } else {
            try {
                val result = remoteDataSource.getPhotos(page, perPage)

                emit(RepositoryState.Success(result.toModel()))
            } catch (e: Exception) {
                emit(RepositoryState.Failure(ErrorType.Failure(e.message)))
            }
        }

    }

    override fun getPhoto(id: String) = flow {
        if (photosInMemory.containsKey(id)) {
            emit(RepositoryState.Success(photosInMemory[id]!!))
        } else if (!networkHelper.isConnectionAvailable()) {
            emit(RepositoryState.Failure(ErrorType.NoConnection))
        } else {
            try {
                val photoResult = remoteDataSource.getPhoto(id)
                val userResult = remoteDataSource.getUser(photoResult.user!!.username!!)
                val photoModel = photoResult.toModel(userResult)

                photosInMemory[id] = photoModel

                emit(RepositoryState.Success(photoModel))
            } catch (e: Exception) {
                emit(RepositoryState.Failure(ErrorType.Failure(e.message)))
            }
        }
    }

}