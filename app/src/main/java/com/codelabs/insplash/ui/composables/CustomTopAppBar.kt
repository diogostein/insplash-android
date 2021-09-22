package com.codelabs.insplash.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.codelabs.insplash.R
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun CustomTopAppBar(bottomContent: (@Composable () -> Unit)? = null) {
    Box {
        Surface(
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier.background(color = MaterialTheme.colors.primarySurface)
            ) {
                TopAppBar(
                    title = {
                        Text(stringResource(id = R.string.app_name))
                    },
                    modifier = Modifier.statusBarsPadding(),
                    elevation = 0.dp,
                )
                bottomContent?.invoke()
            }
        }
        Spacer(
            Modifier
                .background(MaterialTheme.colors.primarySurface)
                .statusBarsHeight()
                .fillMaxWidth()
        )
    }
}

