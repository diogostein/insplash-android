package com.codelabs.insplash.photos

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.codelabs.insplash.ui.composables.CustomTopAppBar

@Composable
fun PhotoDetailScreen(navController: NavController) {
    Scaffold(
        topBar = { CustomTopAppBar() },
    ) {
        Text("Detail screen")
    }
}