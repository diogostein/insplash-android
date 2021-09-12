package com.codelabs.insplash.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codelabs.insplash.app.api.UnsplashApiService
import com.codelabs.insplash.ui.Screen
import com.codelabs.insplash.ui.composables.Layout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@Composable
fun PhotoListScreen(navController: NavController, apiService: UnsplashApiService) {
    Scaffold(
        topBar = { Layout.CustomTopAppBar() },
    ) {
        Button(
            onClick = {
                //navController.navigate(Screen.PhotoDetail.route)
                CoroutineScope(IO).launch {
                    val result = apiService.getPhotos(1, 20)

                    println("Size: ${result.size}")
                    result.forEach { println(it.id) }
                }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Get photos")
        }
    }
}