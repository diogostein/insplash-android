package com.codelabs.insplash.app.di

import com.codelabs.insplash.app.helpers.NetworkHelper
import com.codelabs.insplash.photos.data.PhotoLocalDataSource
import com.codelabs.insplash.photos.data.PhotoRemoteDataSource
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
    fun providePhotoRepository(
        remoteDataSource: PhotoRemoteDataSource,
        localDataSource: PhotoLocalDataSource,
        networkHelper: NetworkHelper
    ): PhotoRepository {
        return PhotoRepositoryImpl(remoteDataSource, localDataSource, networkHelper)
    }

}