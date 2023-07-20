package com.mnatsakanyan.rijksmuseum.artworkdetails

import androidx.lifecycle.SavedStateHandle
import com.mnatsakanyan.domain.artobject.fake.TestFailedGetArtObjectUseCase
import com.mnatsakanyan.domain.artobject.fake.TestGetArtObjectUseCase
import com.mnatsakanyan.rijksmuseum.MainDispatcherRule
import com.mnatsakanyan.rijksmuseum.artworkdetails.navigation.ARTWORK_OBJECT_NUMBER
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class ArtworkDetailsViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainDispatcherRule()

    private val artworkObjectNumber = "artworkOrderNumber"

    private val savedStateHandle =
            SavedStateHandle(mapOf(Pair(ARTWORK_OBJECT_NUMBER, artworkObjectNumber)))

    private lateinit var viewModel: ArtworkDetailsViewModel
    private lateinit var failedViewModel: ArtworkDetailsViewModel

    @Before
    fun setUp() {
        viewModel = ArtworkDetailsViewModel(savedStateHandle, TestGetArtObjectUseCase())
        failedViewModel = ArtworkDetailsViewModel(savedStateHandle, TestFailedGetArtObjectUseCase())
    }

    @Test
    fun uiStateEmitsSuccessWhenGetArtObjectUseCaseCallSucceeds() = runTest {
        val collectorJob = backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect {}
        }

        val stateValue = viewModel.uiState.value
        assertTrue { stateValue is ArtworkDetailsUiState.Artwork }
        assertEquals(artworkObjectNumber,
                     (stateValue as ArtworkDetailsUiState.Artwork).artObject.objectNumber)

        collectorJob.cancel()
    }

    @Test
    fun uiStateEmitsLoadingWhenGetArtObjectUseCaseIsCalled() = runTest {
        assertTrue { viewModel.uiState.value is ArtworkDetailsUiState.Loading }
    }

    @Test
    fun uiStateEmitsErrorWhenGetArtObjectUseCaseCallFailed() = runTest {
        val collectorJob = backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            failedViewModel.uiState.collect {}
        }

        val stateValue = failedViewModel.uiState.value
        assertTrue { stateValue is ArtworkDetailsUiState.Error }

        collectorJob.cancel()
    }
}
