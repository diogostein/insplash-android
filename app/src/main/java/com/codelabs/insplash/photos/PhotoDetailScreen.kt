package com.codelabs.insplash.photos

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.codelabs.insplash.R
import com.codelabs.insplash.app.models.Photo
import com.codelabs.insplash.app.states.UiState
import com.codelabs.insplash.ui.composables.CenteredProgress
import com.codelabs.insplash.ui.composables.ErrorMessage
import com.codelabs.insplash.ui.composables.GlideNetworkImage
import com.codelabs.insplash.ui.composables.StatusBarTheme
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

    when (val state = viewModel.state.value) {
        is UiState.Loading -> CenteredProgress()
        is UiState.Error -> ErrorMessage(message = state.message) {
            viewModel.getPhoto(photoId)
        }
        is UiState.Success<Photo> -> RenderPhoto(state.value) {
            navController.popBackStack()
        }
        else -> Unit
    }
}

@Composable
private fun RenderPhoto(photo: Photo, onBackTap: () -> Unit) {
    StatusBarTheme(darkIcons = false)

    Box(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize()
            .background(Color.Black),
    ) {
        GlideNetworkImage(photo.urls?.full ?: "")
        TopSection(onBackTap)
        BottomSection(photo)
    }
}

@Composable
private fun BoxScope.TopSection(onBackTap: () -> Unit) {
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
                IconButton(onClick = { onBackTap() }) {
                    Icon(Icons.Filled.ArrowBack,
                        contentDescription = "",
                        tint = Color.White.copy(alpha = .6f)
                    )
                }
            },
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
        Surface(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.dp, Color.White, CircleShape)
                .shadow(4.dp, CircleShape)
        ) {
            GlideNetworkImage(photo.user?.profileImage?.medium ?: "")
        }
        Text(photo.user?.name ?: "-",
            color = Color.White,
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.body1.copy(
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(2f, 2f),
                    blurRadius = 4f
                )
            )
        )
        photo.user?.location?.let {
            Row {
                Icon(Icons.Outlined.Place,
                    contentDescription = stringResource(R.string.location),
                    tint = Color.White.copy(alpha = .7f),
                    modifier = Modifier
                        .size(18.dp)
                        .padding(end = 4.dp)
                )
                Text(it,
                    color = Color.White.copy(alpha = .7f),
                    modifier = Modifier.padding(top = 2.dp),
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.body1.copy(
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
        Row {
            Icon(Icons.Outlined.FavoriteBorder,
                contentDescription = stringResource(R.string.likes),
                tint = Color.White,
                modifier = Modifier
                    .size(18.dp)
                    .padding(end = 4.dp)
            )
            Text("${photo.likes}", color = Color.White, fontSize = 12.sp)
            Text("•",
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 12.sp
            )
            Text(
                quantityStringResource(R.plurals.downloads,
                    photo.downloads ?: 0, photo.downloads ?: 0),
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}