package com.mnatsakanyan.rijksmuseum.artworkdetails

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.mnatsakanyan.domain.model.ArtObject
import com.mnatsakanyan.rijksmuseum.R
import com.mnatsakanyan.rijksmuseum.artworkdetails.ArtworkDetailsUiState.Error
import com.mnatsakanyan.rijksmuseum.artworkdetails.ArtworkDetailsUiState.Loading
import com.mnatsakanyan.rijksmuseum.compose.common.ErrorScreen
import com.mnatsakanyan.rijksmuseum.compose.common.LoadingScreen
import com.mnatsakanyan.rijksmuseum.compose.common.MuseumTopBar
import com.mnatsakanyan.rijksmuseum.compose.theme.Dimen.cardCornerRadius
import com.mnatsakanyan.rijksmuseum.compose.theme.Dimen.detailsImageHeight
import com.mnatsakanyan.rijksmuseum.compose.theme.Dimen.paddingLarge
import com.mnatsakanyan.rijksmuseum.compose.theme.Dimen.paddingMedium
import kotlin.math.min

@Composable
internal fun ArtworkDetailsScreen(
        modifier: Modifier = Modifier,
        viewModel: ArtworkDetailsViewModel = hiltViewModel(),
        onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ArtworkDetailsScreen(
            modifier = modifier,
            uiState = uiState,
            onBackClick = onBackClick,
            onRetryButtonClick = { viewModel.onRetryLoading() }
    )
}

@Composable
internal fun ArtworkDetailsScreen(
        modifier: Modifier = Modifier,
        uiState: ArtworkDetailsUiState,
        onBackClick: () -> Unit,
        onRetryButtonClick: () -> Unit
) {
    Scaffold(
            modifier = modifier,
            topBar = {
                MuseumTopBar(
                        title = stringResource(id = R.string.artwork_details_title),
                        navigationIcon = {
                            IconButton(
                                    modifier = Modifier.testTag(stringResource(R.string.artwork_details_back_button)),
                                    onClick = onBackClick
                            ) {
                                Icon(
                                        imageVector = Filled.ArrowBack,
                                        tint = colorScheme.onBackground,
                                        contentDescription = null
                                )
                            }
                        }
                )
            },
            content = { padding ->
                ArtworkDetailsScreenContent(
                        modifier = modifier
                                .padding(padding),
                        uiState = uiState,
                        onRetryButtonClick = onRetryButtonClick
                )
            }
    )
}

@Composable
private fun ArtworkDetailsScreenContent(
        modifier: Modifier = Modifier,
        uiState: ArtworkDetailsUiState,
        onRetryButtonClick: () -> Unit
) {
    when (uiState) {
        is Loading -> LoadingScreen(modifier = modifier)
        is Error -> ErrorScreen(
                modifier = modifier,
                text = stringResource(id = R.string.artwork_details_loading_failed_text),
                onRetryButtonClick = onRetryButtonClick
        )

        is ArtworkDetailsUiState.Artwork -> ArtworkDetailsArtworksScreen(
                modifier = modifier,
                artObject = uiState.artObject,
        )
    }
}

@Composable
private fun ArtworkDetailsArtworksScreen(
        modifier: Modifier = Modifier,
        scrollState: ScrollState = rememberScrollState(),
        artObject: ArtObject
) {
    Column(
            modifier = modifier
                    .fillMaxSize()
                    .padding(paddingLarge)
                    .verticalScroll(scrollState),
    ) {
        artObject.imageUrl?.let { imageUrl ->
            ArtworkDetailsHeaderImage(
                    modifier = Modifier
                            .fillMaxWidth()
                            .height(detailsImageHeight),
                    imageUrl = imageUrl
            )
        }
        Spacer(modifier = Modifier.height(paddingLarge))
        Text(
                text = artObject.title,
                style = typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(paddingMedium))
        ArtworkDetailsPropertiesContent(
                artObject = artObject
        )
    }
}

@Composable
private fun ArtworkDetailsHeaderImage(
        modifier: Modifier = Modifier,
        imageUrl: String
) {
    val painter = rememberAsyncImagePainter(imageUrl)
    val state = painter.state

    val transition by animateFloatAsState(
            targetValue = if (state is AsyncImagePainter.State.Success) 1f else 0f
    )
    Image(
            modifier = modifier
                    .scale(.8f + (.2f * transition))
                    .alpha(min(1f, transition / .2f))
                    .clip(RoundedCornerShape(
                            corner = CornerSize(cardCornerRadius)
                    )),
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop
    )
}

@Composable
private fun ArtworkDetailsPropertiesContent(
        modifier: Modifier = Modifier,
        artObject: ArtObject
) {
    Column(
            modifier = modifier
                    .fillMaxWidth()
    ) {
        ArtworkDetailsProperty(
                headerText = stringResource(id = R.string.artwork_details_author_header),
                bodyText = artObject.author
        )
        artObject.description?.let { description ->
            ArtworkDetailsProperty(
                    headerText = stringResource(id = R.string.artwork_details_description_header),
                    bodyText = description
            )
        }
        artObject.date?.let { date ->
            ArtworkDetailsProperty(
                    headerText = stringResource(id = R.string.artwork_details_date_header),
                    bodyText = date
            )
        }
    }
}

@Composable
private fun ArtworkDetailsProperty(
        modifier: Modifier = Modifier,
        headerText: String,
        bodyText: String
) {
    Column(
            modifier = modifier.padding(vertical = paddingMedium)
    ) {
        Text(
                text = headerText,
                style = typography.titleMedium,
                fontWeight = Bold
        )
        Text(
                text = bodyText,
                style = typography.bodyLarge,
                overflow = TextOverflow.Visible
        )
    }
}

@Preview
@Composable
private fun ArtworkDetailsScreenPreview() {
    ArtworkDetailsScreen(
            uiState = ArtworkDetailsUiState.Artwork(
                    ArtObject(
                            id = "id",
                            imageUrl = "https://www.rijksmuseum.nl/assets/1a43e8aa-5d64-42ae-9567-ad30221d0e1c?w=640&h=1391&fx=2193&fy=3289&format=webp&c=2944e86bf8ac8bcbc3840da6ace9a86759e6f3618ce0bfbce40eeb202437ddb8",
                            objectNumber = "objectNumber",
                            title = "title",
                            author = "author",
                            description = "description",
                            date = "date"
                    )
            ),
            onBackClick = {},
            onRetryButtonClick = {}
    )
}
