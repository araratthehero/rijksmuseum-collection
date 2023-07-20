package com.mnatsakanyan.rijksmuseum.artworkdetails

import com.mnatsakanyan.domain.model.ArtObject

internal sealed interface ArtworkDetailsUiState {
    object Loading : ArtworkDetailsUiState

    object Error : ArtworkDetailsUiState

    data class Artwork(
            val artObject: ArtObject,
    ) : ArtworkDetailsUiState
}
