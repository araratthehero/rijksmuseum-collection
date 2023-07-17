package com.mnatsakanyan.rijksmuseum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.view.WindowCompat.setDecorFitsSystemWindows

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDecorFitsSystemWindows(window, false)

        // TODO: Set up UI
    }
}
