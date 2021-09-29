package com.codelabs.insplash.favorites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.codelabs.insplash.R
import com.codelabs.insplash.app.models.Photo
import com.codelabs.insplash.app.states.UiState
import com.codelabs.insplash.ui.composables.*
import com.google.accompanist.insets.navigationBarsPadding

@Composable
fun FavoriteListScreen(
    navController: NavController,
    viewModel: FavoriteListViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getFavorites()
    }

    StatusBarTheme(darkIcons = true)

    val state = viewModel.state.value

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            Toolbar(
                title = {
                    Text(stringResource(R.string.favorites))
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            )
        },
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            when (state) {
                is UiState.Loading -> CenteredProgress()
                is UiState.Error -> ErrorMessage(message = state.message) {
                    viewModel.getFavorites()
                }
                is UiState.Success<List<Photo>> -> FavoriteList(state.value) {
                    navController.navigate("photos/${it.id}")
                }
                else -> Unit
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun FavoriteList(
    photos: List<Photo>,
    onItemClick: (Photo) -> Unit,
) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(4.dp),
    ) {
        items(photos) { photo ->
            PhotoListFrame(onTap = { onItemClick(photo) }) {
                //BlurImage(photo.blurHash)
                GlideNetworkImage(photo.urls?.regular ?: "")
            }
        }
    }
}