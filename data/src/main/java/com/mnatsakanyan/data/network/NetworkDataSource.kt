package com.mnatsakanyan.data.network

import com.mnatsakanyan.data.model.NetworkArtObject

internal interface NetworkDataSource {

    suspend fun getCollection(pageNumber: Int, itemCountPerPage: Int): List<NetworkArtObject>

    suspend fun getArtObjectDetail(artObjectNumber: String): NetworkArtObject
}
