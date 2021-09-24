package com.codelabs.insplash.favorites.data

import com.codelabs.insplash.app.database.entities.FavoriteEntity
import com.codelabs.insplash.app.remoteResultHandler
import com.codelabs.insplash.app.states.RepositoryState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface FavoriteRepository {
    fun saveFavorite(photoId: String): Flow<RepositoryState<Unit>>
    fun removeFavorite(photoId: String): Flow<RepositoryState<Unit>>
}

class FavoriteRepositoryImpl(
    private val localDataSource: FavoriteLocalDataSource
) : FavoriteRepository {

    override fun saveFavorite(photoId: String) = flow {
        emit(remoteResultHandler {
            localDataSource.insertFavorite(FavoriteEntity(photoId))

            RepositoryState.Success(Unit)
        })
    }

    override fun removeFavorite(photoId: String) = flow {
        emit(remoteResultHandler {
            localDataSource.deleteFavorite(FavoriteEntity(photoId))

            RepositoryState.Success(Unit)
        })
    }

}