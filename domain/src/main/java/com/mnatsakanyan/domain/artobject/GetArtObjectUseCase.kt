package com.mnatsakanyan.domain.artobject

import com.mnatsakanyan.domain.model.ArtObject
import kotlinx.coroutines.flow.Flow

interface GetArtObjectUseCase {
    operator fun invoke(artObjectNumber: String): Flow<ArtObject>
}
