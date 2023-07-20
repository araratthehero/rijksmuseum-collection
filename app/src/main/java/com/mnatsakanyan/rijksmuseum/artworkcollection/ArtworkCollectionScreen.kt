package com.mnatsakanyan.rijksmuseum.artworkcollection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.mnatsakanyan.rijksmuseum.R
import com.mnatsakanyan.rijksmuseum.compose.common.ErrorScreen
import com.mnatsakanyan.rijksmuseum.compose.common.LoadingScreen
import com.mnatsakanyan.rijksmuseum.compose.common.MuseumTopBar
import com.mnatsakanyan.rijksmuseum.compose.theme.Dimen.cardCornerRadius
import com.mnatsakanyan.rijksmuseum.compose.theme.Dimen.paddingLarge
import com.mnatsakanyan.rijksmuseum.compose.theme.Dimen.paddingMedium
import com.mnatsakanyan.rijksmuseum.compose.theme.Dimen.paddingSmall
import com.mnatsakanyan.rijksmuseum.compose.theme.Dimen.thumbnailSize

@Composable
internal fun ArtworkCollectionScreen(
        modifier: Modifier = Modifier,
        viewModel: ArtworkCollectionViewModel = hiltViewModel(),
        onArtworkClick: (String) -> Unit = {}
) {
    val artObjects = viewModel.artObjects.collectAsLazyPagingItems()

    ArtworkCollectionScreen(
            modifier = modifier,
            artObjects = artObjects,
            onArtworkClick = onArtworkClick,
            onRetryButtonClick = { artObjects.retry() }
    )
}

@Composable
internal fun ArtworkCollectionScreen(
        modifier: Modifier = Modifier,
        artObjects: LazyPagingItems<ArtworkCollectionListItem>,
        onArtworkClick: (String) -> Unit = {},
        onRetryButtonClick: () -> Unit
) {
    Scaffold(
            modifier = modifier,
            topBar = {
                MuseumTopBar(
                        title = stringResource(id = R.string.app_title)
                )
            },
            content = { padding ->
                ArtworkCollectionScreenContent(
                        modifier = modifier.padding(padding),
                        artObjects = artObjects,
                        onArtworkClick = onArtworkClick,
                        onRetryButtonClick = onRetryButtonClick
                )
            }
    )
}

@Composable
private fun ArtworkCollectionScreenContent(
        modifier: Modifier = Modifier,
        artObjects: LazyPagingItems<ArtworkCollectionListItem>,
        onArtworkClick: (String) -> Unit,
        onRetryButtonClick: () -> Unit
) {
    LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                    horizontal = paddingLarge,
                    vertical = paddingMedium
            )
    ) {
        items(
                count = artObjects.itemCount
        ) { index ->
            when (val listItem = artObjects[index]) {
                is ArtworkCollectionListItem.ArtworkCollectionItem -> ArtworkListItem(
                        item = listItem,
                        onArtworkClick = onArtworkClick
                )
                is ArtworkCollectionListItem.ArtworkCollectionAuthor -> AuthorListItem(
                        author = listItem
                )
                else -> { /* No need to draw anything */ }
            }
        }

        when (artObjects.loadState.refresh) {
            is LoadState.Loading -> item { ArtworkListItemInitialLoading() }
            is LoadState.Error -> item {
                ArtworkListItemInitialError(onRetryButtonClick = onRetryButtonClick)
            }
            else -> { /* Could be implemented based on design requirements */ }
        }

        when (artObjects.loadState.append) {
            is LoadState.Loading -> item { ArtworkListItemAppendLoading() }
            is LoadState.Error -> item {
                ArtworkListItemAppendError(onRetryButtonClick = onRetryButtonClick)
            }
            else -> { /* Could be implemented based on design requirements */ }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ArtworkListItem(
        modifier: Modifier = Modifier,
        item: ArtworkCollectionListItem.ArtworkCollectionItem,
        onArtworkClick: (String) -> Unit = {}
) {
    Card(
            modifier = modifier
                    .padding(paddingMedium)
                    .fillMaxWidth()
                    .testTag(stringResource(R.string.artwork_collection_card)),
            onClick = { onArtworkClick.invoke(item.objectNumber) },
            shape = RoundedCornerShape(
                    corner = CornerSize(cardCornerRadius)
            )
    ) {
        Row(
                modifier = Modifier
                        .padding(paddingMedium)
                        .fillMaxWidth()
        ) {
            item.imageUrl?.let { imageUrl ->
                ThumbnailImage(
                        modifier = Modifier.padding(paddingMedium),
                        imageUrl = imageUrl
                )
            }
            Column(
                    modifier = Modifier
                            .fillMaxWidth()
                            .padding(paddingMedium)
            ) {
                Text(
                        text = item.title,
                        maxLines = 2,
                        overflow = Ellipsis,
                        style = typography.titleLarge
                )
                Text(
                        text = item.author,
                        maxLines = 1,
                        overflow = Ellipsis,
                        style = typography.bodySmall
                )
            }
        }
    }
}

@Composable
private fun AuthorListItem(
        modifier: Modifier = Modifier,
        author: ArtworkCollectionListItem.ArtworkCollectionAuthor
) {
    Text(
            modifier = modifier
                    .padding(top = paddingSmall)
                    .padding(paddingMedium)
                    .fillMaxWidth(),
            text = author.name,
            maxLines = 2,
            overflow = Ellipsis,
            style = typography.headlineMedium
    )
}

@Composable
private fun ThumbnailImage(
        modifier: Modifier = Modifier,
        imageUrl: String
) {
    AsyncImage(
            modifier = modifier
                    .size(thumbnailSize)
                    .clip(RoundedCornerShape(
                            corner = CornerSize(cardCornerRadius)
                    )),
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
    )
}

@Composable
private fun LazyItemScope.ArtworkListItemInitialLoading(
        modifier: Modifier = Modifier
) {
    LoadingScreen(modifier = modifier.fillParentMaxSize())
}

@Composable
private fun LazyItemScope.ArtworkListItemInitialError(
        modifier: Modifier = Modifier,
        onRetryButtonClick: () -> Unit
) {
    ErrorScreen(
            modifier = modifier.fillParentMaxSize(),
            text = stringResource(id = R.string.artwork_collection_loading_failed_text),
            onRetryButtonClick = onRetryButtonClick
    )
}

@Composable
private fun ArtworkListItemAppendLoading(
        modifier: Modifier = Modifier
) {
    LoadingScreen(
            modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = paddingLarge),
            text = stringResource(id = R.string.artwork_collection_loading_more_items_text)
    )
}

@Composable
private fun ArtworkListItemAppendError(
        modifier: Modifier = Modifier,
        onRetryButtonClick: () -> Unit
) {
    ErrorScreen(
            modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = paddingLarge),
            text = stringResource(id = R.string.artwork_collection_loading_more_failed_text),
            painter = painterResource(id = R.drawable.ic_sad_face),
            onRetryButtonClick = onRetryButtonClick
    )
}

@Preview
@Composable
private fun ArtworkListItemPreview() {
    ArtworkListItem(
            item = ArtworkCollectionListItem.ArtworkCollectionItem(
                    imageUrl = "https://www.rijksmuseum.nl/assets/1a43e8aa-5d64-42ae-9567-ad30221d0e1c?w=640&h=1391&fx=2193&fy=3289&format=webp&c=2944e86bf8ac8bcbc3840da6ace9a86759e6f3618ce0bfbce40eeb202437ddb8",
                    objectNumber = "",
                    title = "Title",
                    author = "Maker"
            )
    )
}

@Preview
@Composable
private fun AuthorListItemPreview() {
    AuthorListItem(
            author = ArtworkCollectionListItem.ArtworkCollectionAuthor(
                    name = "Maker"
            )
    )
}
