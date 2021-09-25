package com.codelabs.insplash.favorites.data

import com.codelabs.insplash.app.database.entities.FavoriteEntity
import com.codelabs.insplash.app.database.relationships.toModel
import com.codelabs.insplash.app.models.Photo
import com.codelabs.insplash.app.repositoryResultHandler
import com.codelabs.insplash.app.states.RepositoryState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface FavoriteRepository {
    fun getFavorites(): Flow<RepositoryState<List<Photo>>>
    fun saveFavorite(photoId: String): Flow<RepositoryState<Unit>>
    fun removeFavorite(photoId: String): Flow<RepositoryState<Unit>>
}

class FavoriteRepositoryImpl(
    private val localDataSource: FavoriteLocalDataSource
) : FavoriteRepository {

    override fun getFavorites() = flow {
        emit(repositoryResultHandler {
            val result = localDataSource.getFavorites()

            RepositoryState.Success(result.toModel())
        })
    }

    override fun saveFavorite(photoId: String) = flow {
        emit(repositoryResultHandler {
            localDataSource.insertFavorite(FavoriteEntity(photoId))

            RepositoryState.Success(Unit)
        })
    }

    override fun removeFavorite(photoId: String) = flow {
        emit(repositoryResultHandler {
            localDataSource.deleteFavorite(FavoriteEntity(photoId))

            RepositoryState.Success(Unit)
        })
    }

}