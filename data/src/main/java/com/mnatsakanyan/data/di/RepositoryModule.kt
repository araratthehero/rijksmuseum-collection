package com.mnatsakanyan.data.di

import com.mnatsakanyan.data.network.NetworkDataSource
import com.mnatsakanyan.data.repository.artobject.ArtObjectRepository
import com.mnatsakanyan.data.repository.artobject.DefaultArtObjectRepository
import com.mnatsakanyan.data.repository.artobjectCollection.ArtObjectCollectionRepository
import com.mnatsakanyan.data.repository.artobjectCollection.DefaultArtObjectCollectionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    private const val PAGE_SIZE = 15

    @Singleton
    @Provides
    fun providesArtObjectCollectionRepository(
            networkDataSource: NetworkDataSource
    ): ArtObjectCollectionRepository = DefaultArtObjectCollectionRepository(
            networkDataSource,
            PAGE_SIZE,
            Dispatchers.IO
    )

    @Singleton
    @Provides
    fun providesArtObjectRepository(
            networkDataSource: NetworkDataSource
    ): ArtObjectRepository = DefaultArtObjectRepository(
            networkDataSource,
            Dispatchers.IO
    )
}
