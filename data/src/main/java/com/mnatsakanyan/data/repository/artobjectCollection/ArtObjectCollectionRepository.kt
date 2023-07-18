package com.mnatsakanyan.data.repository.artobjectCollection

import androidx.paging.PagingData
import com.mnatsakanyan.data.model.RequestedArtObject
import kotlinx.coroutines.flow.Flow

interface ArtObjectCollectionRepository {

    fun fetchArtObjectCollectionList(): Flow<PagingData<RequestedArtObject>>
}
