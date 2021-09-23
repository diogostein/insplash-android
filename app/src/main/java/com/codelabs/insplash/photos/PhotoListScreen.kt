package com.codelabs.insplash.photos

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.codelabs.insplash.R
import com.codelabs.insplash.app.Const
import com.codelabs.insplash.app.states.UiState
import com.codelabs.insplash.app.models.Photo
import com.codelabs.insplash.ui.composables.*
import com.google.accompanist.insets.navigationBarsPadding

@Composable
fun PhotoListScreen(navController: NavController, viewModel: PhotoListViewModel = hiltViewModel()) {
    StatusBarTheme(darkIcons = true)

    val state = viewModel.state.value
    val query = viewModel.query.value

    var hideSearchField by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        topBar = {
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
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(MaterialTheme.colors.primarySurface)
                ) {
                    ChipGroup(
                        chipValues = Const.Seed.topics.map { ChipValue(it.title!!, it.slug!!) },
                        selectedValue = query,
                        onChanged = {
                            hideSearchField = true
                            viewModel.getPhotos(true, it)
                        }
                    )
                }
            }
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
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

@Composable
private fun PhotoListFrame(onTap: (() -> Unit)? = null, content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier
            .height(200.dp)
            .padding(4.dp)
            .clickable(onClick = { onTap?.invoke() }),
        color = MaterialTheme.colors.secondary.copy(.05f),
        shape = RoundedCornerShape(8.dp)
    ) {
        content()
    }
}