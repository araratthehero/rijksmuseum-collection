package com.mnatsakanyan.rijksmuseum.artworkcollection.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mnatsakanyan.rijksmuseum.artworkcollection.ArtworkCollectionScreen

const val artworkCollectionNavigationRoute = "artwork_collection_route"

fun NavGraphBuilder.artworkCollectionScreen(onArtworkClick: (String) -> Unit) {
    composable(
            route = artworkCollectionNavigationRoute
    ) {
        ArtworkCollectionScreen(onArtworkClick = onArtworkClick)
    }
}
