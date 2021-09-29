package com.codelabs.insplash.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.codelabs.insplash.app.utils.BlurHashDecoder
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun BlurImage(blurHash: String?, width: Int = 200, height: Int = 200) {
    val blurBitmap = remember {
        mutableStateOf(BlurHashDecoder.decode(blurHash, width, height))
    }

    blurBitmap.value?.let {
        GlideImage(
            imageModel = it,
            requestOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop(),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
        )
    }
}