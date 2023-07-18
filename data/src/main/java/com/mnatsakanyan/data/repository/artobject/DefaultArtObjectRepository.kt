package com.mnatsakanyan.data.repository.artobject

import com.mnatsakanyan.data.model.RequestedArtObject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultArtObjectRepository @Inject constructor(
        private val dispatcher: CoroutineDispatcher
) : ArtObjectRepository {
    // TODO: Implement the content here
    override fun fetchArtObject(artObjectNumber: String): Flow<RequestedArtObject> {
        TODO("Not yet implemented")
    }
}
