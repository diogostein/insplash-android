package com.codelabs.insplash.photos

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.codelabs.insplash.R
import com.codelabs.insplash.app.models.Photo
import com.codelabs.insplash.app.states.UiState
import com.codelabs.insplash.ui.composables.*
import com.codelabs.insplash.ui.composables.helpers.quantityStringResource
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun PhotoDetailScreen(
    navController: NavController,
    photoId: String,
    viewModel: PhotoDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getPhoto(photoId)
    }

    val state = viewModel.state.value
    val favoriteState = viewModel.isFavorite.value

    when (state) {
        is UiState.Loading -> CenteredProgress()
        is UiState.Error -> ErrorMessage(message = state.message) {
            viewModel.getPhoto(photoId)
        }
        is UiState.Success<Photo> -> RenderPhoto(state.value, favoriteState, {
            navController.popBackStack()
        }, { photo, isFavorite ->
            viewModel.setFavorite(photo.id!!, isFavorite)
        })
        else -> Unit
    }
}

@Composable
private fun RenderPhoto(
    photo: Photo,
    isFavorite: Boolean,
    onBackTap: () -> Unit,
    onFavoriteTap: (Photo, Boolean) -> Unit,
) {
    StatusBarTheme(darkIcons = false)

    val photoColor = Color(android.graphics.Color.parseColor(photo.color))

    var showForeground by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize()
            .background(photoColor)
    ) {
        Clickable(onClick = { showForeground = !showForeground }) {
            GlideNetworkImage(photo.urls?.full ?: "", modifier = Modifier.background(photoColor))
        }

        if (showForeground) {
            TopSection(isFavorite, onBackTap) { onFavoriteTap(photo, isFavorite) }
            BottomSection(photo)
        }
    }
}

@Composable
private fun BoxScope.TopSection(
    isFavorite: Boolean,
    onBackTap: () -> Unit,
    onFavoriteTap: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.TopCenter)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = .6f),
                        Color.Transparent
                    )
                )
            )
            .statusBarsPadding()
    ) {
        TopAppBar(
            title = { Text("") },
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            navigationIcon = {
                IconButton(onClick = onBackTap) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        stringResource(R.string.back),
                        tint = Color.White.copy(alpha = .6f)
                    )
                }
            },
            actions = {
                IconButton(onClick = {onFavoriteTap()}) {
                    Icon(
                        if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        stringResource(R.string.favorite),
                        tint = if (isFavorite) Color(0xffff7d7a) else Color.White.copy(alpha = .7f),
                    )
                }
            }
        )
    }
}

@Composable
private fun BoxScope.BottomSection(photo: Photo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black.copy(alpha = .8f)
                    )
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircleAvatar(url = photo.user?.profileImage?.medium ?: "")
        Text(photo.user?.name ?: "-",
            color = Color.White,
            style = MaterialTheme.typography.h6.copy(
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(2f, 2f),
                    blurRadius = 4f
                )
            )
        )
        photo.user?.location?.let {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(Icons.Outlined.Place,
                    contentDescription = stringResource(R.string.location),
                    tint = Color(0xFFDDDDDD),
                    modifier = Modifier
                        .size(18.dp)
                        .padding(end = 4.dp)
                )
                Text(it,
                    color = Color(0xFFDDDDDD),
                    style = MaterialTheme.typography.subtitle2.copy(
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(2f, 2f),
                            blurRadius = 4f
                        )
                    )
                )
            }
        }
        Spacer(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .size(width = 30.dp, height = 1.dp)
                .background(Color.White)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Outlined.FavoriteBorder,
                contentDescription = stringResource(R.string.likes),
                tint = Color.White,
                modifier = Modifier
                    .size(18.dp)
                    .padding(end = 4.dp)
            )
            Text("${photo.likes}",
                color = Color.White,
                fontSize = MaterialTheme.typography.caption.fontSize
            )
            Text("•",
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = MaterialTheme.typography.caption.fontSize
            )
            Text(
                quantityStringResource(R.plurals.downloads,
                    photo.downloads ?: 0, photo.downloads ?: 0),
                color = Color.White,
                fontSize = MaterialTheme.typography.caption.fontSize
            )
        }
    }
}