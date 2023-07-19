package com.mnatsakanyan.data.repository

import androidx.paging.PagingData
import com.mnatsakanyan.data.model.RequestedArtObject
import kotlinx.coroutines.flow.Flow

interface ArtObjectRepository {

    fun fetchArtObjectCollection(): Flow<PagingData<RequestedArtObject>>

    fun fetchArtObject(artObjectNumber: String): Flow<RequestedArtObject>
}
