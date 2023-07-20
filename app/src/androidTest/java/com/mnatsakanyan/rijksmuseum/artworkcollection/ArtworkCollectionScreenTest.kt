package com.mnatsakanyan.rijksmuseum.artworkcollection

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.mnatsakanyan.rijksmuseum.R
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

class ArtworkCollectionScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun showsListOfItemsWhenArtObjectsAvailable() {
        val artworkCollectionListItemsList = generateListOfArtworkCollectionMixedItems(4)

        composeTestRule.setContent {
            val artObjects =
                    flowOf(generatePagingData(artworkCollectionListItemsList)).collectAsLazyPagingItems()

            ArtworkCollectionScreen(
                    artObjects = artObjects,
                    onArtworkClick = {},
                    onRetryButtonClick = {}
            )
        }

        artworkCollectionListItemsList.forEach { listItem ->
            val text = when (listItem) {
                is ArtworkCollectionListItem.ArtworkCollectionItem -> listItem.title
                is ArtworkCollectionListItem.ArtworkCollectionAuthor -> listItem.name
            }
            composeTestRule.onNodeWithText(text).assertExists()
        }
    }

    @Test
    fun showsErrorScreenWhenArtObjectsIsNotAvailable() {
        val loadStates = LoadStates(
                refresh = LoadState.Error(Exception()),
                prepend = LoadState.NotLoading(false),
                append = LoadState.NotLoading(false)
        )
        val pagingData = PagingData.from(listOf<ArtworkCollectionListItem>(), loadStates)

        composeTestRule.setContent {
            val artObjects = flowOf(pagingData)

            ArtworkCollectionScreen(
                    artObjects = artObjects.collectAsLazyPagingItems(),
                    onArtworkClick = {},
                    onRetryButtonClick = {}
            )
        }

        composeTestRule.onNodeWithText(
                composeTestRule.activity.resources.getString(R.string.artwork_collection_loading_failed_text)
        ).assertExists()
    }

    private fun generatePagingData(artworkCollectionListItemsList: List<ArtworkCollectionListItem>) =
            PagingData.from(artworkCollectionListItemsList)

    private fun generateListOfArtworkCollectionMixedItems(sizeOfList: Int) =
            (0..sizeOfList).map { index ->
                if (index % 3 == 0) {
                    generateArtworkCollectionAuthor(index.toString())
                } else {
                    generateArtworkCollectionItem(index.toString())
                }
            }

    private fun generateArtworkCollectionItem(title: String) =
            ArtworkCollectionListItem.ArtworkCollectionItem(
                    title = title,
                    imageUrl = "https://www.rijksmuseum.nl/assets/1a43e8aa-5d64-42ae-9567-ad30221d0e1c?w=640&h=1391&fx=2193&fy=3289&format=webp&c=2944e86bf8ac8bcbc3840da6ace9a86759e6f3618ce0bfbce40eeb202437ddb8",
                    objectNumber = "objectNumber",
                    author = "author"
            )

    private fun generateArtworkCollectionAuthor(authorName: String) =
            ArtworkCollectionListItem.ArtworkCollectionAuthor(
                    name = authorName
            )
}
