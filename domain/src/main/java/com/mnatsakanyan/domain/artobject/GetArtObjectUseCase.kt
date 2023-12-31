package com.mnatsakanyan.domain.artobject

import com.mnatsakanyan.domain.model.ArtObject
import kotlinx.coroutines.flow.Flow
import com.mnatsakanyan.domain.model.Result

interface GetArtObjectUseCase {
    operator fun invoke(artObjectNumber: String): Flow<Result<ArtObject>>
}
