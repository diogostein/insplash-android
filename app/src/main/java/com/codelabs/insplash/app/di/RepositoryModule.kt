package com.codelabs.insplash.app.di

import com.codelabs.insplash.app.api.UnsplashApiService
import com.codelabs.insplash.photos.data.PhotoRepository
import com.codelabs.insplash.photos.data.PhotoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun providePhotoRepository(apiService: UnsplashApiService): PhotoRepository {
        return PhotoRepositoryImpl(apiService)
    }

}