package com.mnatsakanyan.domain.model

import com.mnatsakanyan.data.model.RequestedArtObject

data class ArtObject(
        val id: String,
        val imageUrl: String?,
        val objectNumber: String,
        val title: String,
        val author: String,
        val description: String?,
        val date: String?
)

internal fun RequestedArtObject.asExternalModel() = ArtObject(
        id = id,
        imageUrl = imageUrl,
        objectNumber = objectNumber,
        title = title,
        author = principalOrFirstMaker,
        description = description,
        date = date
)
