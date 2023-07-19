package com.mnatsakanyan.data.network.fake

import com.mnatsakanyan.data.model.NetworkArtObject
import com.mnatsakanyan.data.network.NetworkDataSource
import java.io.IOException

internal class TestFailedNetworkDataSource : NetworkDataSource {

    override suspend fun getCollection(pageNumber: Int,
                                       itemCountPerPage: Int): List<NetworkArtObject> {
        throw IOException()
    }

    override suspend fun getArtObjectDetail(artObjectNumber: String): NetworkArtObject {
        throw IOException()
    }
}
