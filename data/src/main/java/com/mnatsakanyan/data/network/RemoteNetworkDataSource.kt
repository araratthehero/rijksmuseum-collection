package com.mnatsakanyan.data.network

import javax.inject.Inject

class RemoteNetworkDataSource @Inject constructor(private val museumCollectionService: MuseumCollectionService) :
    NetworkDataSource {

    override suspend fun getCollectionList(pageNumber: Int,
                                           itemCountPerPage: Int) =
            museumCollectionService.getCollectionList(pageNumber, itemCountPerPage).artObjects

    override suspend fun getArtObjectDetail(artObjectNumber: String) =
            museumCollectionService.getArtObjectDetail(artObjectNumber).artObject

}
