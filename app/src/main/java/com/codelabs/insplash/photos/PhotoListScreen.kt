package com.codelabs.insplash.photos

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.codelabs.insplash.R
import com.codelabs.insplash.app.Const
import com.codelabs.insplash.app.states.UiState
import com.codelabs.insplash.app.models.Photo
import com.codelabs.insplash.photos.dialogs.AboutDialog
import com.codelabs.insplash.ui.composables.*
import com.google.accompanist.insets.navigationBarsPadding

@Composable
fun PhotoListScreen(navController: NavController, viewModel: PhotoListViewModel = hiltViewModel()) {
    StatusBarTheme(darkIcons = true)

    val state = viewModel.state.value
    val query = viewModel.query.value

    Scaffold(
        topBar = {
            TopBar(navController, viewModel, query)
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
        ) {
            when (state) {
                is UiState.Loading -> CenteredProgress()
                is UiState.Error -> ErrorMessage(message = state.message) {
                    viewModel.getPhotos(true, query)
                }
                is UiState.Success<List<Photo>> -> PhotoList(state, {
                    navController.navigate("photos/${it.id}")
                }, {
                    viewModel.getPhotos(query = query)
                })
                else -> Unit
            }
        }
    }
}

@Composable
private fun TopBar(navController: NavController, viewModel: PhotoListViewModel, query: String?) {
    var hideSearchField by rememberSaveable { mutableStateOf(true) }
    var showMenu by remember { mutableStateOf(false) }

    val showAboutDialog = remember { mutableStateOf(false) }

    AboutDialog(showAboutDialog)

    CustomTopAppBar(
        initialSearchText = query,
        hideSearchField = hideSearchField,
        onSearch = {
            hideSearchField = false
            viewModel.getPhotos(true, it)
        },
        onCancelSearch = {
            viewModel.getPhotos(true)
        },
        actions = {
            Box {
                IconButton(onClick = { showMenu = true }) {
                    Icon(Icons.Filled.MoreVert, null)
                }
                PopupMenu(
                    menuItems = listOf(
                        stringResource(R.string.favorites),
                        stringResource(R.string.about)
                    ),
                    onClickCallbacks = listOf({
                        navController.navigate("favorites")
                    }, {
                        showAboutDialog.value = true
                    }),
                    showMenu = showMenu,
                    onDismiss = { showMenu = false }) {
                }
            }
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(MaterialTheme.colors.primarySurface),
        ) {
            ChipGroup(
                chipValues = Const.Seed.topics.map { ChipValue(it.title!!, it.slug!!) },
                selectedValue = query,
                onChanged = {
                    hideSearchField = true
                    viewModel.getPhotos(true, it)
                }
            )
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(30.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                MaterialTheme.colors.primary,
                                Color.Transparent
                            )
                        )
                    )
                    .align(Alignment.TopStart)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(30.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colors.primary
                            )
                        )
                    )
                    .align(Alignment.TopEnd)
            )
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
        cells = GridCells.Adaptive(150.dp),
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
                    //BlurImage(photo.blurHash)
                    GlideNetworkImage(photo.urls?.regular ?: "")
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