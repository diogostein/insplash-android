package com.codelabs.insplash.photos

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.codelabs.insplash.app.states.UiState
import com.codelabs.insplash.app.api.responses.PhotoResponse
import com.codelabs.insplash.ui.composables.CenteredProgress
import com.codelabs.insplash.ui.composables.CustomTopAppBar
import com.codelabs.insplash.ui.composables.ErrorMessage
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun PhotoListScreen(navController: NavController, viewModel: PhotoListViewModel = hiltViewModel()) {
    val state = viewModel.state.value

    Scaffold(
        topBar = { CustomTopAppBar() },
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (state) {
                is UiState.Loading -> CenteredProgress()
                is UiState.Error -> ErrorMessage(message = state.message) {
                    viewModel.getPhotos(1, 30)
                }
                is UiState.Success<*> -> PhotoList((state as UiState.Success<List<PhotoResponse>>).value) {
                    viewModel.getPhotos(1, 30)
                }
                else -> Unit
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PhotoList(photos: List<PhotoResponse>, onItemClick: (PhotoResponse) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(photos) {
            Surface(
                modifier = Modifier
                    .padding(4.dp)
                    .clickable(onClick = { onItemClick(it) }),
                color = Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            ) {
                GlideImage(
                    imageModel = it.urls!!.regular!!,
                    requestOptions = RequestOptions()
                        .override(256, 256)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop(),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                )
            }
        }
    }
}