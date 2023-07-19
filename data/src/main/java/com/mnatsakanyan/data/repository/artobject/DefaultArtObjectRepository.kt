package com.mnatsakanyan.data.repository.artobject

import com.mnatsakanyan.data.model.RequestedArtObject
import com.mnatsakanyan.data.model.asExternalModel
import com.mnatsakanyan.data.network.NetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class DefaultArtObjectRepository @Inject constructor(
        private val networkDataSource: NetworkDataSource,
        private val dispatcher: CoroutineDispatcher
) : ArtObjectRepository {

    override fun fetchArtObject(artObjectNumber: String): Flow<RequestedArtObject> =
            flow {
                val artObject = networkDataSource.getArtObjectDetail(artObjectNumber)
                        .asExternalModel()
                emit(artObject)
            }.flowOn(dispatcher)
}

