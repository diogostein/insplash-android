package com.codelabs.insplash.ui.composables

import androidx.compose.runtime.Composable

@Composable
fun Visibility(visible: Boolean, content: @Composable () -> Unit) {
    if (visible) content() else Unit
}