package com.mnatsakanyan.rijksmuseum.compose.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mnatsakanyan.rijksmuseum.R

@Composable
internal fun LoadingScreen(
        modifier: Modifier = Modifier,
        text: String = stringResource(id = R.string.loading_text)
) {
    MessageScreen(
            modifier = modifier.fillMaxSize(),
            header = {
                CircularProgressIndicator(
                        color = colorScheme.onBackground
                )
            },
            text = text
    )
}

@Preview
@Composable
private fun LoadingScreenPreview() {
    LoadingScreen()
}
