package com.codelabs.insplash.app.api.responses

import com.squareup.moshi.Json

data class PhotoResponse(
    @field:Json(name = "id") val id: String?,
    @field:Json(name = "created_at") val createdAt: String?,
    @field:Json(name = "updated_at") val updatedAt: String?,
    @field:Json(name = "width") val width: Int?,
    @field:Json(name = "height") val height: Int?,
    @field:Json(name = "color") val color: String?,
    @field:Json(name = "blur_hash") val blurHash: String?,
    @field:Json(name = "likes") val likes: Int?,
    @field:Json(name = "liked_by_user") val likedBuUser: Boolean?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "urls") val urls: PhotoUrlsResponse?,
)
