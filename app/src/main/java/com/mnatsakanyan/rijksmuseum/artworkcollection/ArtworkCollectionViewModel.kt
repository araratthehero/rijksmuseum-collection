package com.mnatsakanyan.rijksmuseum.artworkcollection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.mnatsakanyan.domain.artobjectcollection.GetArtObjectCollectionUseCase
import com.mnatsakanyan.domain.model.ArtObject
import com.mnatsakanyan.rijksmuseum.artworkcollection.ArtworkCollectionListItem.ArtworkCollectionAuthor
import com.mnatsakanyan.rijksmuseum.artworkcollection.ArtworkCollectionListItem.ArtworkCollectionItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
internal class ArtworkCollectionViewModel @Inject constructor(
        getArtObjectCollectionUseCase: GetArtObjectCollectionUseCase
) : ViewModel() {

    val artObjects: Flow<PagingData<ArtworkCollectionListItem>> =
            getArtObjectCollectionUseCase().map { pagingData ->
                pagingData.mapToArtworkCollectionItem().insertSeparators()
            }.cachedIn(viewModelScope)

    private fun PagingData<ArtObject>.mapToArtworkCollectionItem() = map { artObject ->
        artObject.toArtCollectionItem()
    }

    private fun PagingData<ArtworkCollectionItem>.insertSeparators() =
            insertSeparators { before, after ->
                when {
                    before == null && after != null ->
                        ArtworkCollectionAuthor(after.author)

                    before != null && after != null && shouldSeparate(before, after) ->
                        ArtworkCollectionAuthor(after.author)

                    else -> null
                }
            }

    private fun shouldSeparate(
            before: ArtworkCollectionItem,
            after: ArtworkCollectionItem
    ): Boolean = before.author != after.author
}
