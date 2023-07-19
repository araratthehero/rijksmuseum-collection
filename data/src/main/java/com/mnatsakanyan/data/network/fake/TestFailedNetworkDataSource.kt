package com.mnatsakanyan.data.network.fake

import com.mnatsakanyan.data.model.NetworkArtObject
import com.mnatsakanyan.data.network.NetworkDataSource

internal class TestFailedNetworkDataSource : NetworkDataSource {

    override suspend fun getCollectionList(pageNumber: Int,
                                           itemCountPerPage: Int): List<NetworkArtObject> {
        throw Exception()
    }

    override suspend fun getArtObjectDetail(artObjectNumber: String): NetworkArtObject {
        throw Exception()
    }
}
