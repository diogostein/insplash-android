package com.codelabs.insplash.photos.data

import com.codelabs.insplash.app.ErrorType
import com.codelabs.insplash.app.api.responses.toEntity
import com.codelabs.insplash.app.api.responses.toModel
import com.codelabs.insplash.app.database.relationships.toModel
import com.codelabs.insplash.app.helpers.NetworkHelper
import com.codelabs.insplash.app.models.Photo
import com.codelabs.insplash.app.repositoryResultHandler
import com.codelabs.insplash.app.states.RepositoryState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface PhotoRepository {
    fun getPhotos(page: Int, perPage: Int): Flow<RepositoryState<List<Photo>>>
    fun getPhoto(id: String): Flow<RepositoryState<Photo>>
    fun searchPhotos(query: String, page: Int, perPage: Int): Flow<RepositoryState<List<Photo>>>
}

class PhotoRepositoryImpl(
    private val remoteDataSource: PhotoRemoteDataSource,
    private val localDataSource: PhotoLocalDataSource,
    private val networkHelper: NetworkHelper,
) : PhotoRepository {

    override fun getPhotos(page: Int, perPage: Int) = flow {
        if (!networkHelper.isConnectionAvailable()) {
            emit(RepositoryState.Failure(ErrorType.NoConnection))
        } else {
            emit(repositoryResultHandler {
                val result = remoteDataSource.getPhotos(page, perPage)

                localDataSource.insertPhotos(result.toEntity())

                RepositoryState.Success(result.toModel())
            })
        }
    }

    override fun getPhoto(id: String) = flow {
        emit(repositoryResultHandler {
            val photoFromDb = localDataSource.getPhoto(id)

            if (photoFromDb?.user != null) {
                RepositoryState.Success(photoFromDb.toModel())
            } else if (!networkHelper.isConnectionAvailable()) {
                RepositoryState.Failure(ErrorType.NoConnection)
            } else {
                val photoResult = remoteDataSource.getPhoto(id)
                val userResult = remoteDataSource.getUser(photoResult.user!!.username!!)
                val photoModel = photoResult.toModel(userResult)

                localDataSource.insertPhotos(listOf(photoResult.toEntity()))
                localDataSource.insertUser(userResult.toEntity())

                RepositoryState.Success(photoModel)
            }
        })
    }

    override fun searchPhotos(query: String, page: Int, perPage: Int) = flow {
        if (!networkHelper.isConnectionAvailable()) {
            emit(RepositoryState.Failure(ErrorType.NoConnection))
        } else {
            emit(repositoryResultHandler {
                val result = remoteDataSource.searchPhotos(query, page, perPage)

                localDataSource.insertPhotos(result.results.toEntity())

                RepositoryState.Success(result.results.toModel())
            })
        }
    }

}