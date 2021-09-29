package com.codelabs.insplash.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.asImageBitmap
import com.codelabs.insplash.app.utils.BlurHashDecoder

@Composable
fun BlurImage(blurHash: String?, width: Int = 200, height: Int = 200) {
    val blurBitmap = remember {
        mutableStateOf(BlurHashDecoder.decode(blurHash, width, height))
    }

    blurBitmap.value?.let {
        Image(it.asImageBitmap(), contentDescription = null)
    }
}