package com.mnatsakanyan.data.network

import javax.inject.Inject

internal class RemoteNetworkDataSource @Inject constructor(
        private val collectionService: CollectionService
) : NetworkDataSource {

    override suspend fun getCollection(pageNumber: Int, itemCountPerPage: Int) =
            collectionService.getCollection(pageNumber, itemCountPerPage).artObjects

    override suspend fun getArtObjectDetail(artObjectNumber: String) =
            collectionService.getArtObjectDetail(artObjectNumber).artObject

}
