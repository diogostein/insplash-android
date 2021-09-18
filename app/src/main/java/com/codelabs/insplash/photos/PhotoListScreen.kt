package com.codelabs.insplash.photos

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.codelabs.insplash.R
import com.codelabs.insplash.app.Const
import com.codelabs.insplash.app.states.UiState
import com.codelabs.insplash.app.models.Photo
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
                    viewModel.getPhotos(reload = true)
                }
                is UiState.Success<List<Photo>> -> PhotoList(state, {
                    viewModel.getPhotos(reload = true)
                }, {
                    viewModel.getPhotos()
                })
                else -> Unit
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PhotoList(
    state: UiState.Success<List<Photo>>,
    onItemClick: (Photo) -> Unit,
    onLoadMore: () -> Unit,
) {
    val count = state.value.size

    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp),
    ) {
        items(count + 1) { index ->
            if (index >= count && state !is UiState.Pagination) {
                onLoadMore()
            }

            if (index >= count) {
                when (state) {
                    is UiState.PaginationLoading -> PhotoListFrame(content = { CenteredProgress() })
                    is UiState.PaginationError -> PhotoListFrame(content = {
                        ErrorMessage { onLoadMore() }
                    })
                    is UiState.PaginationFinished -> PhotoListFrame(content = { NoMoreResults() })
                    else -> Unit
                }
            } else {
                val photo = state.value[index]

                PhotoListFrame(onTap = { onItemClick(photo) }) {
                    GlideImage(
                        imageModel = photo.urls!!.regular!!,
                        requestOptions = RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .centerCrop(),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center,
                    )
                }
            }
        }
    }
}

@Composable
private fun NoMoreResults() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            stringResource(R.string.no_more_results),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun PhotoListFrame(onTap: (() -> Unit)? = null, content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier
            .height(200.dp)
            .padding(4.dp)
            .clickable(onClick = { onTap?.invoke() }),
        color = Color(0x0D000000),
        shape = RoundedCornerShape(8.dp)
    ) {
        content()
    }
}