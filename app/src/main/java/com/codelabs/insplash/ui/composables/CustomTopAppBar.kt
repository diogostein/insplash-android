package com.codelabs.insplash.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.codelabs.insplash.R
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun CustomTopAppBar() {
    Box {
        TopAppBar(
            title = {
                Text(stringResource(id = R.string.app_name))
            },
            modifier = Modifier.statusBarsPadding(),
        )
        Spacer(
            Modifier
                .background(MaterialTheme.colors.primarySurface)
                .statusBarsHeight()
                .fillMaxWidth()
        )
    }

}