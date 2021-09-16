package com.codelabs.insplash.app.models

import java.util.*

data class Photo(
    val id: String? = null,
    val createdAt: Date? = null,
    val updatedAt: Date? = null,
    val width: Int? = null,
    val height: Int? = null,
    val color: String? = null,
    val blurHash: String? = null,
    val likes: Int? = null,
    val likedByUser: Boolean? = null,
    val description: String? = null,
    val urls: PhotoUrls? = null,
)