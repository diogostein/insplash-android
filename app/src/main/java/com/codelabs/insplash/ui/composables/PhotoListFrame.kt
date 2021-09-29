package com.codelabs.insplash.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PhotoListFrame(
    onTap: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .height(180.dp)
            .padding(4.dp)
            .clickable(onClick = { onTap?.invoke() }),
        color = MaterialTheme.colors.secondary.copy(.05f),
        shape = RoundedCornerShape(8.dp)
    ) {
        content()
    }
}