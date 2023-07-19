package com.mnatsakanyan.rijksmuseum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat.setDecorFitsSystemWindows
import com.mnatsakanyan.rijksmuseum.compose.theme.RijksmuseumTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDecorFitsSystemWindows(window, false)

        setContent {
            RijksmuseumTheme {
                // TODO: Add nav host
            }
        }
    }
}
