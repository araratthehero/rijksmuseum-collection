package com.mnatsakanyan.rijksmuseum.artworkcollection

import androidx.paging.testing.asSnapshot
import com.mnatsakanyan.domain.artobjectcollection.fake.TestGetArtObjectCollectionUseCase
import com.mnatsakanyan.rijksmuseum.MainDispatcherRule
import com.mnatsakanyan.rijksmuseum.artworkcollection.ArtworkCollectionListItem.ArtworkCollectionAuthor
import com.mnatsakanyan.rijksmuseum.artworkcollection.ArtworkCollectionListItem.ArtworkCollectionItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class ArtworkCollectionViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainDispatcherRule()

    private val listSize = 20
    private val breakBetweenAuthors = 5

    private val getArtObjectCollectionListUseCase = TestGetArtObjectCollectionUseCase(
            sizeOfList = listSize,
            breakBetweenAuthors = breakBetweenAuthors
    )

    private lateinit var viewModel: ArtworkCollectionViewModel

    @Before
    fun setUp() {
        viewModel = ArtworkCollectionViewModel(getArtObjectCollectionListUseCase)
    }

    @Test
    fun artObjectsEmitAddsSeparatorForRepeatingAuthor() = runTest {
        val result = viewModel.artObjects.asSnapshot()

        assertTrue { result.first() is ArtworkCollectionAuthor }
        assertIfSeparatorIsAdded(result, 1)
    }

    @Test
    fun artObjectsEmitAddsNewSeparatorForNextRepeatingAuthor() = runTest {
        val result = viewModel.artObjects.asSnapshot()

        assertIfSeparatorIsAdded(result, 1)
        assertIfSeparatorIsAdded(result, 2)
        assertIfSeparatorIsAdded(result, 3)
    }

    private fun assertIfSeparatorIsAdded(result: List<ArtworkCollectionListItem>, pageNumber: Int) {
        val authorPosition = breakBetweenAuthors * pageNumber

        assertTrue { result[authorPosition - 1] is ArtworkCollectionItem }
        assertTrue { result[authorPosition] is ArtworkCollectionAuthor }
        assertTrue { result[authorPosition + 1] is ArtworkCollectionItem }

        assertNotEquals(
                result[authorPosition - 1] as ArtworkCollectionItem,
                result[authorPosition + 1] as ArtworkCollectionItem
        )
    }
}
