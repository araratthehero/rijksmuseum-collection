package com.mnatsakanyan.rijksmuseum.compose.common

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MuseumTopBar(
        modifier: Modifier = Modifier,
        title: String,
        navigationIcon: @Composable () -> Unit = {}
) {
    CenterAlignedTopAppBar(
            modifier = modifier,
            title = {
                Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = navigationIcon
    )
}
