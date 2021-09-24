package com.codelabs.insplash.photos.data

import com.codelabs.insplash.app.CustomException
import com.codelabs.insplash.app.database.InsplashDatabase
import com.codelabs.insplash.app.database.entities.PhotoEntity
import com.codelabs.insplash.app.database.entities.UserEntity
import com.codelabs.insplash.app.database.relationships.PhotoAndUser

interface PhotoLocalDataSource {
    suspend fun insertPhotos(photos: List<PhotoEntity>)
    suspend fun insertUser(user: UserEntity)
    suspend fun getPhoto(id: String): PhotoAndUser?
}

class PhotoLocalDataSourceImpl(private val database: InsplashDatabase) : PhotoLocalDataSource {
    override suspend fun insertPhotos(photos: List<PhotoEntity>) {
        database.photoDao().insertAll(photos)
    }

    override suspend fun insertUser(user: UserEntity) {
        database.userDao().insert(user)
    }

    override suspend fun getPhoto(id: String): PhotoAndUser? {
        return try {
            database.photoDao().getPhotoAndUser(id)
        } catch (e: Exception) {
            throw CustomException.Database(e.message)
        }
    }

}