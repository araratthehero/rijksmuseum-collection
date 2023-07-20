package com.mnatsakanyan.rijksmuseum

import com.mnatsakanyan.domain.artobject.GetArtObjectUseCase
import com.mnatsakanyan.domain.artobject.fake.TestGetArtObjectUseCase
import com.mnatsakanyan.domain.artobjectcollection.GetArtObjectCollectionUseCase
import com.mnatsakanyan.domain.artobjectcollection.fake.TestGetArtObjectCollectionUseCase
import com.mnatsakanyan.domain.di.UseCaseModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
        components = [SingletonComponent::class],
        replaces = [UseCaseModule::class],
)
object TestUseCaseModule {

    @Singleton
    @Provides
    fun providesGetArtObjectCollectionUseCase(): GetArtObjectCollectionUseCase =
            TestGetArtObjectCollectionUseCase()

    @Singleton
    @Provides
    fun providesGetArtObjectUseCase(): GetArtObjectUseCase = TestGetArtObjectUseCase()
}
