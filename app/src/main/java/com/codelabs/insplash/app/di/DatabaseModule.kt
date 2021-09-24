package com.codelabs.insplash.app.di

import android.content.Context
import androidx.room.Room
import com.codelabs.insplash.app.database.InsplashDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): InsplashDatabase {
        return Room.databaseBuilder(
            context,
            InsplashDatabase::class.java, "insplash"
        ).build()
    }

}