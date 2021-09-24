package com.codelabs.insplash.app.database.daos

import androidx.room.*
import com.codelabs.insplash.app.database.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

}