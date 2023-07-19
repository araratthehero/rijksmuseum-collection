package com.mnatsakanyan.rijksmuseum.compose.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mnatsakanyan.rijksmuseum.R
import com.mnatsakanyan.rijksmuseum.compose.theme.Dimen.paddingMedium

@Composable
internal fun ErrorScreen(
        modifier: Modifier = Modifier,
        text: String,
        painter: Painter = painterResource(id = R.drawable.ic_failed_doughnut),
        onRetryButtonClick: () -> Unit
) {
    MessageScreen(
            modifier = modifier
                    .fillMaxSize(),
            text = text,
            header = {
                Icon(
                        modifier = Modifier.padding(paddingMedium),
                        painter = painter,
                        contentDescription = null,
                        tint = colorScheme.onBackground
                )
            },
            footer = {
                Button(
                        modifier = Modifier.padding(paddingMedium),
                        onClick = { onRetryButtonClick.invoke() }
                ) {
                    Text(text = "Retry")
                }
            }
    )
}

@Preview
@Composable
private fun ErrorScreenPreview() {
    ErrorScreen(
            text = "Failed text"
    ) {}
}
