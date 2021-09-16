package com.codelabs.insplash.app.di

import com.codelabs.insplash.app.api.UnsplashApiService
import com.codelabs.insplash.photos.data.PhotoRemoteDataSource
import com.codelabs.insplash.photos.data.PhotoRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DataSourceModule {

    @ViewModelScoped
    @Provides
    fun providePhotoRemoteDataSource(apiService: UnsplashApiService): PhotoRemoteDataSource {
        return PhotoRemoteDataSourceImpl(apiService)
    }

}