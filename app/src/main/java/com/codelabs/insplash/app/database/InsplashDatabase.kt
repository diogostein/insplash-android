package com.codelabs.insplash.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codelabs.insplash.app.database.daos.PhotoDao
import com.codelabs.insplash.app.database.daos.UserDao
import com.codelabs.insplash.app.database.entities.PhotoEntity
import com.codelabs.insplash.app.database.entities.ProfileImageEntity
import com.codelabs.insplash.app.database.entities.UrlsEntity
import com.codelabs.insplash.app.database.entities.UserEntity

@Database(version = 1, entities = [
    UserEntity::class,
    PhotoEntity::class,
])
abstract class InsplashDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun photoDao(): PhotoDao
}