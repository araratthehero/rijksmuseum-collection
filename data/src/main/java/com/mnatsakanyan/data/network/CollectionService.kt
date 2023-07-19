package com.mnatsakanyan.data.network

import com.mnatsakanyan.data.model.NetworkArtObjectCollectionResponse
import com.mnatsakanyan.data.model.NetworkArtObjectResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface CollectionService {

    @GET("collection?s=artist")
    suspend fun getCollection(
            @Query("p")
            pageNumber: Int,
            @Query("ps")
            itemCountPerPage: Int
    ): NetworkArtObjectCollectionResponse

    @GET("collection/{artObjectNumber}")
    suspend fun getArtObjectDetail(
            @Path("artObjectNumber")
            artObjectNumber: String
    ): NetworkArtObjectResponse
}
