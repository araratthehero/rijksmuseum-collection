package com.mnatsakanyan.rijksmuseum.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mnatsakanyan.rijksmuseum.artworkcollection.navigation.artworkCollectionNavigationRoute
import com.mnatsakanyan.rijksmuseum.artworkcollection.navigation.artworkCollectionScreen
import com.mnatsakanyan.rijksmuseum.artworkdetails.navigation.artworkDetailsScreen
import com.mnatsakanyan.rijksmuseum.artworkdetails.navigation.navigateToArtworkDetails

@Composable
fun RijksmuseumNavHost(
        modifier: Modifier = Modifier,
        navController: NavHostController = rememberNavController(),
        startDestination: String = artworkCollectionNavigationRoute,
) {
    NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier,
    ) {
        artworkCollectionScreen(onArtworkClick = navController::navigateToArtworkDetails)
        artworkDetailsScreen(onBackClick = navController::navigateUp)
    }
}
