package com.mnatsakanyan.data.di

import com.mnatsakanyan.data.network.NetworkDataSource
import com.mnatsakanyan.data.repository.ArtObjectRepository
import com.mnatsakanyan.data.repository.DefaultArtObjectRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

    private const val PAGE_SIZE = 15

    @Singleton
    @Provides
    fun providesArtObjectRepository(
            networkDataSource: NetworkDataSource
    ): ArtObjectRepository = DefaultArtObjectRepository(
            networkDataSource,
            PAGE_SIZE,
            Dispatchers.IO
    )
}
