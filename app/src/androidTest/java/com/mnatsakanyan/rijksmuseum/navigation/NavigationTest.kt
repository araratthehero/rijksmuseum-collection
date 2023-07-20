package com.mnatsakanyan.rijksmuseum.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.mnatsakanyan.rijksmuseum.MainActivity
import com.mnatsakanyan.rijksmuseum.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NavigationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun firstScreenIsArtworkCollection() {
        composeTestRule.apply {
            onNodeWithText(composeTestRule.activity.resources.getString(R.string.app_title)).assertExists()
        }
    }

    @Test
    fun clickingOnArtworkItemNavigatesToArtworkDetails() {
        composeTestRule.apply {
            val artworkCollectionCard = getString(R.string.artwork_collection_card)

            onAllNodesWithTag(artworkCollectionCard).onFirst().performClick()

            onNodeWithText(composeTestRule.activity.resources.getString(R.string.artwork_details_title)).assertExists()
        }
    }

    @Test
    fun backFromArtworkDetailsReturnsToArtworkCollection() {
        composeTestRule.apply {
            val artworkCollectionCard = getString(R.string.artwork_collection_card)
            val artworkDetailsBackButton = getString(R.string.artwork_details_back_button)

            onAllNodesWithTag(artworkCollectionCard).onFirst().performClick()
            onNodeWithTag(artworkDetailsBackButton).performClick()

            onNodeWithText(composeTestRule.activity.resources.getString(R.string.app_title)).assertExists()
        }
    }

    private fun AndroidComposeTestRule<*, *>.getString(@StringRes id: Int) =
            activity.resources.getString(id)
}
