package com.mnatsakanyan.data.di

import com.mnatsakanyan.data.network.NetworkDataSource
import com.mnatsakanyan.data.network.RemoteNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataStoreModule {

    @Binds
    fun bindMuseumNetworkDataSource(
            remoteMuseumNetworkDataSource: RemoteNetworkDataSource
    ): NetworkDataSource
}
