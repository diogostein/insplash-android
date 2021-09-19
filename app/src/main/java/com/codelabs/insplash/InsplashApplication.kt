package com.codelabs.insplash

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class InsplashApplication : Application() {
    companion object {
        lateinit var instance: InsplashApplication private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}