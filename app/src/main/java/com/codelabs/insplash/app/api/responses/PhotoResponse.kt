package com.codelabs.insplash.app.api.responses

import com.codelabs.insplash.app.api.utils.toDateTime
import com.codelabs.insplash.app.models.Photo
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
    @field:Json(name = "liked_by_user") val likedByUser: Boolean?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "urls") val urls: PhotoUrlsResponse?,
)

fun PhotoResponse.toModel(): Photo {
    return Photo(
        id = id,
        createdAt = createdAt?.toDateTime(),
        updatedAt = updatedAt?.toDateTime(),
        width = width,
        height = height,
        color = color,
        blurHash = blurHash,
        likes = likes,
        likedByUser = likedByUser,
        description = description,
        urls = urls?.toModel()
    )
}

fun List<PhotoResponse>.toModel() = map { it.toModel() }
