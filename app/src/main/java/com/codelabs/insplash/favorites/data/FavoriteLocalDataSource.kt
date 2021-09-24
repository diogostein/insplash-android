package com.codelabs.insplash.favorites.data

import com.codelabs.insplash.app.database.InsplashDatabase
import com.codelabs.insplash.app.database.entities.FavoriteEntity
import com.codelabs.insplash.app.localResultHandler


interface FavoriteLocalDataSource {
    suspend fun insertFavorite(favorite: FavoriteEntity)
    suspend fun deleteFavorite(favorite: FavoriteEntity)
}

class FavoriteLocalDataSourceImpl(private val database: InsplashDatabase): FavoriteLocalDataSource {

    override suspend fun insertFavorite(favorite: FavoriteEntity) {
        return localResultHandler {
            database.favoriteDao().insert(favorite)
        }
    }

    override suspend fun deleteFavorite(favorite: FavoriteEntity) {
        return localResultHandler {
            database.favoriteDao().delete(favorite)
        }
    }

}