package com.codelabs.insplash.screens

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.codelabs.insplash.ui.composables.Layout

@Composable
fun PhotoDetailScreen(navController: NavController) {
    Scaffold(
        topBar = { Layout.CustomTopAppBar() },
    ) {
        Text("Detail screen")
    }
}