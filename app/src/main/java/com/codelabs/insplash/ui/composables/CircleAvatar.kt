package com.codelabs.insplash.ui.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircleAvatar(url: String, size: Dp = 40.dp) {
    Surface(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .border(1.dp, Color.White, CircleShape)
            .shadow(4.dp, CircleShape)
    ) {
        GlideNetworkImage(url)
    }
}