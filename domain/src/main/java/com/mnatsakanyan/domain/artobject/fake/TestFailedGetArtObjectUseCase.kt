package com.mnatsakanyan.domain.artobject.fake

import com.mnatsakanyan.domain.artobject.GetArtObjectUseCase
import com.mnatsakanyan.domain.model.ArtObject
import com.mnatsakanyan.domain.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class TestFailedGetArtObjectUseCase : GetArtObjectUseCase {

    override operator fun invoke(artObjectNumber: String): Flow<Result<ArtObject>> = flow {
        emit(Result.Error(IOException()))
    }
}
