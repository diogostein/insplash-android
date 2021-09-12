package com.codelabs.insplash.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codelabs.insplash.app.api.UnsplashApiService
import com.codelabs.insplash.screens.PhotoDetailScreen
import com.codelabs.insplash.screens.PhotoListScreen
import javax.inject.Inject

sealed class Screen(val route: String) {
    object PhotoList : Screen("photos")
    object PhotoDetail : Screen("photo-detail")
}

@Composable
fun Navigator(apiService: UnsplashApiService) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.PhotoList.route) {
        composable(route = Screen.PhotoList.route) { PhotoListScreen(navController, apiService) }
        composable(route = Screen.PhotoDetail.route) { PhotoDetailScreen(navController) }
    }
}