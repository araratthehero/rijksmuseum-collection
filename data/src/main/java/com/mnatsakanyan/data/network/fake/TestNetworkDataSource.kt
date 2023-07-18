package com.mnatsakanyan.data.network.fake

import com.mnatsakanyan.data.model.NetworkArtObject
import com.mnatsakanyan.data.model.NetworkImage
import com.mnatsakanyan.data.network.NetworkDataSource

class TestNetworkDataSource(
        sizeOfList: Int = 0
) : NetworkDataSource {

    internal val listOfItems = (0..sizeOfList).map { id -> generateNetworkArtObject(id.toString()) }

    override suspend fun getCollectionList(pageNumber: Int,
                                           itemCountPerPage: Int): List<NetworkArtObject> {
        val fromIndex = (pageNumber - 1) * itemCountPerPage
        val toIndex = fromIndex + itemCountPerPage
        return listOfItems.subList(fromIndex, toIndex)
    }

    override suspend fun getArtObjectDetail(artObjectNumber: String) =
            generateNetworkArtObject(artObjectNumber = artObjectNumber)

    private fun generateNetworkArtObject(id: String = "id",
                                         artObjectNumber: String = "objectNumber") =
            NetworkArtObject(
                    id = id,
                    objectNumber = artObjectNumber,
                    title = "title",
                    principalOrFirstMaker = "principalOrFirstMaker",
                    webImage = NetworkImage(
                            guid = "guid",
                            width = null,
                            height = null,
                            url = "url"
                    )
            )
}
