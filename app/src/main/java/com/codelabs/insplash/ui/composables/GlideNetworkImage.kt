package com.codelabs.insplash.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun GlideNetworkImage(url: String) {
    GlideImage(
        imageModel = url,
        requestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop(),
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center,
        circularReveal = CircularReveal(duration = 250)
    )
}