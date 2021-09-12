package com.codelabs.insplash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.codelabs.insplash.app.api.UnsplashApiService
import com.codelabs.insplash.ui.Navigator
import com.codelabs.insplash.ui.theme.InsplashTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var apiService: UnsplashApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InsplashTheme { Navigator(apiService) }
        }
    }
}