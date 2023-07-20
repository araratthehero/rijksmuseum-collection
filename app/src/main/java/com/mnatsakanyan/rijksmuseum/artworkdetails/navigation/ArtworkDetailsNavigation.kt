package com.mnatsakanyan.rijksmuseum.artworkdetails.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mnatsakanyan.rijksmuseum.artworkdetails.ArtworkDetailsScreen

const val ARTWORK_OBJECT_NUMBER = "artworkObjectNumber"
const val artworkDetailsNavigationRoute = "artworkDetails/"

fun NavController.navigateToArtworkDetails(artworkId: String) {
    val encodedId = Uri.encode(artworkId)
    this.navigate("$artworkDetailsNavigationRoute$encodedId")
}

fun NavGraphBuilder.artworkDetailsScreen(
        onBackClick: () -> Unit
) {
    composable(
            route = "$artworkDetailsNavigationRoute{$ARTWORK_OBJECT_NUMBER}",
            arguments = listOf(
                    navArgument(ARTWORK_OBJECT_NUMBER) { type = NavType.StringType },
            ),
    ) {
        ArtworkDetailsScreen(onBackClick = onBackClick)
    }
}
