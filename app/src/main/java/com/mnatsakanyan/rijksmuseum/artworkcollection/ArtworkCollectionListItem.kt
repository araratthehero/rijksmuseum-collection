package com.mnatsakanyan.rijksmuseum.artworkcollection

import com.mnatsakanyan.domain.model.ArtObject

internal sealed class ArtworkCollectionListItem {
    data class ArtworkCollectionItem(
            val title: String,
            val imageUrl: String?,
            val objectNumber: String,
            val author: String
    ) : ArtworkCollectionListItem()

    class ArtworkCollectionAuthor(val name: String) : ArtworkCollectionListItem()
}

internal fun ArtObject.toArtCollectionItem() = ArtworkCollectionListItem.ArtworkCollectionItem(
        title = title,
        imageUrl = imageUrl,
        objectNumber = objectNumber,
        author = author,
)
