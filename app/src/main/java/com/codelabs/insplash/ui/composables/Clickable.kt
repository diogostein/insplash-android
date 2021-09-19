package com.codelabs.insplash.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Clickable(onClick: () -> Unit, content: @Composable () -> Unit) {
    Box(
        modifier = Modifier.clickable { onClick() }
    ) {
        content()
    }
}