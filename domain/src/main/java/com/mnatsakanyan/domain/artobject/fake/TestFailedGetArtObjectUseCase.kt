package com.mnatsakanyan.domain.artobject.fake

import com.mnatsakanyan.domain.artobject.GetArtObjectUseCase
import com.mnatsakanyan.domain.model.ArtObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestFailedGetArtObjectUseCase : GetArtObjectUseCase {

    override operator fun invoke(artObjectNumber: String): Flow<ArtObject> = flow {
        throw Exception()
    }
}
