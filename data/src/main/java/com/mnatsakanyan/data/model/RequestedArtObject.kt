package com.mnatsakanyan.data.model

data class RequestedArtObject(
        val id: String,
        val imageUrl: String?,
        val objectNumber: String,
        val title: String,
        val principalOrFirstMaker: String
)

fun NetworkArtObject.asExternalModel() = RequestedArtObject(
        id = id,
        imageUrl = webImage?.url,
        objectNumber = objectNumber,
        title = title,
        principalOrFirstMaker = principalOrFirstMaker
)
