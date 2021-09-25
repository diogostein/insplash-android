package com.codelabs.insplash.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun Toolbar(
    title: @Composable () -> Unit,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    Box {
        Surface(
            elevation = 8.dp,
            modifier = Modifier.statusBarsPadding(),
        ) {
            Column(
                modifier = Modifier.background(color = MaterialTheme.colors.primarySurface)
            ) {
                TopAppBar(
                    elevation = 0.dp,
                    title = title,
                    navigationIcon = navigationIcon,
                    actions = actions
                )
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