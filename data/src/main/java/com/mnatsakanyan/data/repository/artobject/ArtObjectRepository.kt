package com.mnatsakanyan.data.repository.artobject

import com.mnatsakanyan.data.model.RequestedArtObject
import kotlinx.coroutines.flow.Flow

interface ArtObjectRepository {

    fun fetchArtObject(artObjectNumber: String): Flow<RequestedArtObject>
}
