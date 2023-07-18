package com.mnatsakanyan.domain.artobjectcollection

import androidx.paging.PagingData
import com.mnatsakanyan.domain.model.ArtObject
import kotlinx.coroutines.flow.Flow

interface GetArtObjectCollectionListUseCase {
    operator fun invoke(): Flow<PagingData<ArtObject>>
}
