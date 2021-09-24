package com.codelabs.insplash.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codelabs.insplash.app.database.daos.FavoriteDao
import com.codelabs.insplash.app.database.daos.PhotoDao
import com.codelabs.insplash.app.database.daos.UserDao
import com.codelabs.insplash.app.database.entities.FavoriteEntity
import com.codelabs.insplash.app.database.entities.PhotoEntity
import com.codelabs.insplash.app.database.entities.UserEntity

@Database(version = 1, entities = [
    UserEntity::class,
    PhotoEntity::class,
    FavoriteEntity::class,
])
abstract class InsplashDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun photoDao(): PhotoDao
    abstract fun favoriteDao(): FavoriteDao
}