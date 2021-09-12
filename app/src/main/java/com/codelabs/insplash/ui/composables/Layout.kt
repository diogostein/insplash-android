package com.codelabs.insplash.ui.composables

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

object Layout {
    @Composable
    fun CustomTopAppBar() {
        TopAppBar(
            title = {
                Text("Insplash")
            }
        )
    }
}