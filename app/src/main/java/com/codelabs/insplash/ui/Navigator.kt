package com.codelabs.insplash.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codelabs.insplash.favorites.FavoriteListScreen
import com.codelabs.insplash.photos.PhotoDetailScreen
import com.codelabs.insplash.photos.PhotoListScreen

sealed class Screen(val route: String) {
    object PhotoList : Screen("photos")
    object PhotoDetail : Screen("photos/{id}")
    object FavoriteList : Screen("favorites")
}

@Composable
fun Navigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.PhotoList.route) {
        composable(route = Screen.PhotoList.route) { PhotoListScreen(navController) }
        composable(route = Screen.FavoriteList.route) { FavoriteListScreen(navController) }
        composable(route = Screen.PhotoDetail.route) {
            PhotoDetailScreen(navController, it.arguments?.getString("id")!!)
        }
    }
}