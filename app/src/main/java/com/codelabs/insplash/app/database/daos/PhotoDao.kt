package com.codelabs.insplash.app.database.daos

import androidx.room.*
import com.codelabs.insplash.app.database.entities.PhotoEntity
import com.codelabs.insplash.app.database.relationships.PhotoAndUser

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos: List<PhotoEntity>)

    @Transaction
    @Query("SELECT * FROM photos WHERE id = :id")
    suspend fun getPhotoAndUser(id: String): PhotoAndUser?

}