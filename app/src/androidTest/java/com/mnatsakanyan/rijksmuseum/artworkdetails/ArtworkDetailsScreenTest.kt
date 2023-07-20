package com.mnatsakanyan.rijksmuseum.artworkdetails

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.mnatsakanyan.domain.model.ArtObject
import com.mnatsakanyan.rijksmuseum.R
import com.mnatsakanyan.rijksmuseum.artworkdetails.ArtworkDetailsUiState.Artwork
import com.mnatsakanyan.rijksmuseum.artworkdetails.ArtworkDetailsUiState.Error
import com.mnatsakanyan.rijksmuseum.artworkdetails.ArtworkDetailsUiState.Loading
import org.junit.Rule
import org.junit.Test

class ArtworkDetailsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val testArtObject = ArtObject(
            id = "id",
            imageUrl = "https://www.rijksmuseum.nl/assets/1a43e8aa-5d64-42ae-9567-ad30221d0e1c?w=640&h=1391&fx=2193&fy=3289&format=webp&c=2944e86bf8ac8bcbc3840da6ace9a86759e6f3618ce0bfbce40eeb202437ddb8",
            objectNumber = "objectNumber",
            title = "title",
            author = "author",
            description = "description",
            date = "date"
    )

    @Test
    fun progressScreenIsShownWhenScreenIsLoading() {
        composeTestRule.setContent {
            ArtworkDetailsScreen(
                    uiState = Loading,
                    onBackClick = {},
                    onRetryButtonClick = {}
            )
        }

        composeTestRule
                .onNodeWithText(
                        composeTestRule.activity.resources.getString(R.string.loading_text)
                )
                .assertExists()
    }

    @Test
    fun errorScreenIsShownWhenScreenLoadingFailed() {
        composeTestRule.setContent {
            ArtworkDetailsScreen(
                    uiState = Error,
                    onBackClick = {},
                    onRetryButtonClick = {}
            )
        }

        composeTestRule
                .onNodeWithText(
                        composeTestRule.activity.resources.getString(R.string.artwork_details_loading_failed_text)
                )
                .assertExists()
    }

    @Test
    fun contentScreenIsShownWhenScreenLoadingSucceeds() {
        composeTestRule.setContent {
            ArtworkDetailsScreen(
                    uiState = Artwork(testArtObject),
                    onBackClick = {},
                    onRetryButtonClick = {}
            )
        }

        composeTestRule.onNodeWithText(testArtObject.title).assertExists()
    }
}
