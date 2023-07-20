package com.mnatsakanyan.rijksmuseum.artworkdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnatsakanyan.domain.artobject.GetArtObjectUseCase
import com.mnatsakanyan.domain.model.ArtObject
import com.mnatsakanyan.domain.model.Result
import com.mnatsakanyan.domain.model.Result.Error
import com.mnatsakanyan.domain.model.Result.Loading
import com.mnatsakanyan.domain.model.Result.Success
import com.mnatsakanyan.rijksmuseum.artworkdetails.navigation.ARTWORK_OBJECT_NUMBER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.Lazily
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class ArtworkDetailsViewModel @Inject constructor(
        savedStateHandle: SavedStateHandle,
        getArtObjectUseCase: GetArtObjectUseCase
) : ViewModel() {

    private val artworkObjectNumber: String = checkNotNull(savedStateHandle[ARTWORK_OBJECT_NUMBER])

    private val _triggerGetArtObjectUseCase = MutableSharedFlow<Unit>(
            replay = 1,
            extraBufferCapacity = 0,
            onBufferOverflow = DROP_OLDEST
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<ArtworkDetailsUiState> = _triggerGetArtObjectUseCase
            .flatMapLatest { getArtObjectUseCase(artworkObjectNumber).mapToUiState() }
            .stateIn(
                    scope = viewModelScope,
                    started = Lazily,
                    initialValue = ArtworkDetailsUiState.Loading,
            )

    init {
        onRetryLoading()
    }

    fun onRetryLoading() {
        _triggerGetArtObjectUseCase.tryEmit(Unit)
    }

    private fun Flow<Result<ArtObject>>.mapToUiState() = map { result ->
        when (result) {
            is Success -> ArtworkDetailsUiState.Artwork(artObject = result.data)
            is Loading -> ArtworkDetailsUiState.Loading
            is Error -> ArtworkDetailsUiState.Error
        }
    }
}
