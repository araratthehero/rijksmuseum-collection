package com.mnatsakanyan.rijksmuseum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.view.WindowCompat.setDecorFitsSystemWindows
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDecorFitsSystemWindows(window, false)

        // TODO: Set up UI
    }
}
