package com.mnatsakanyan.data.network

import com.mnatsakanyan.data.model.NetworkArtObject

interface NetworkDataSource {

    suspend fun getCollectionList(pageNumber: Int, itemCountPerPage: Int): List<NetworkArtObject>

    suspend fun getArtObjectDetail(artObjectNumber: String): NetworkArtObject
}
