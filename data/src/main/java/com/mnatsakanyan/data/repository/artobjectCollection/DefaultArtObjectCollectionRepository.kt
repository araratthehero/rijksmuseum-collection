package com.mnatsakanyan.data.repository.artobjectCollection

import androidx.paging.PagingData
import com.mnatsakanyan.data.model.RequestedArtObject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultArtObjectCollectionRepository @Inject constructor(
        private val dispatcher: CoroutineDispatcher
) : ArtObjectCollectionRepository {

    override fun fetchArtObjectCollectionList(): Flow<PagingData<RequestedArtObject>> {
        TODO("Not yet implemented")
    }
}
