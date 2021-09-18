package com.codelabs.insplash.ui.composables

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.codelabs.insplash.R

@Composable
fun CustomTopAppBar() {
    TopAppBar(
        title = {
            Text(stringResource(id = R.string.app_name))
        },
    )
}