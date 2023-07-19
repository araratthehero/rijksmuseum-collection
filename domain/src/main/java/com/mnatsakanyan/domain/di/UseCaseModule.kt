package com.mnatsakanyan.domain.di

import com.mnatsakanyan.domain.artobject.GetArtObjectUseCase
import com.mnatsakanyan.domain.artobject.GetArtObjectUseCaseImpl
import com.mnatsakanyan.domain.artobjectcollection.GetArtObjectCollectionUseCase
import com.mnatsakanyan.domain.artobjectcollection.GetArtObjectCollectionUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface UseCaseModule {

    @Binds
    fun bindGetArtObjectCollectionUseCase(
            getArtObjectCollectionUseCaseImpl: GetArtObjectCollectionUseCaseImpl
    ): GetArtObjectCollectionUseCase

    @Binds
    fun bindGetArtObjectUseCase(
            getArtObjectUseCaseImpl: GetArtObjectUseCaseImpl
    ): GetArtObjectUseCase
}
