package com.codelabs.insplash.app.database.daos

import androidx.room.*
import com.codelabs.insplash.app.database.entities.FavoriteEntity
import com.codelabs.insplash.app.database.relationships.PhotoAndUserAndFavorite

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favorite: FavoriteEntity)

    @Delete
    suspend fun delete(favorite: FavoriteEntity)

    @Query("SELECT * FROM photos WHERE id IN (SELECT favorite_photo_id FROM favorites)")
    suspend fun getPhotos(): List<PhotoAndUserAndFavorite>

}