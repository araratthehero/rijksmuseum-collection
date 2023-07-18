package com.mnatsakanyan.data.repository.artobject.fake

import com.mnatsakanyan.data.model.RequestedArtObject
import com.mnatsakanyan.data.model.asExternalModel
import com.mnatsakanyan.data.network.fake.TestNetworkDataSource
import com.mnatsakanyan.data.repository.artobject.ArtObjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestArtObjectRepository : ArtObjectRepository {

    private val testNetworkDataSource = TestNetworkDataSource()

    override fun fetchArtObject(artObjectNumber: String): Flow<RequestedArtObject> = flow {
        emit(testNetworkDataSource.getArtObjectDetail(artObjectNumber = artObjectNumber)
                     .asExternalModel())
    }
}
