package com.mnatsakanyan.data.model

internal data class NetworkArtObject(
        val id: String,
        val objectNumber: String,
        val title: String,
        val principalOrFirstMaker: String,
        val webImage: NetworkImage?
)
