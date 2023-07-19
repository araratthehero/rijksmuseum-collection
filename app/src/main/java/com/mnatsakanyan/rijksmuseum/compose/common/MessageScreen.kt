package com.mnatsakanyan.rijksmuseum.compose.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.mnatsakanyan.rijksmuseum.compose.theme.Dimen.paddingMedium

@Composable
internal fun MessageScreen(
        modifier: Modifier = Modifier,
        text: String,
        header: @Composable () -> Unit = {},
        footer: @Composable () -> Unit = {}
) {
    Column(
            modifier = modifier.background(colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
        header()
        Text(
                modifier = Modifier.padding(paddingMedium),
                style = typography.titleMedium,
                color = colorScheme.onBackground,
                text = text,
                textAlign = TextAlign.Center
        )
        footer()
    }
}
