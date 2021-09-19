package com.codelabs.insplash.app.api.responses

import com.codelabs.insplash.app.models.ProfileImage
import com.squareup.moshi.Json

data class ProfileImageResponse(
    @field:Json(name = "small") val small: String?,
    @field:Json(name = "medium") val medium: String?,
    @field:Json(name = "large") val large: String?,
)

fun ProfileImageResponse.toModel(): ProfileImage {
    return ProfileImage(
        small = small,
        medium = medium,
        large = large,
    )
}

fun List<ProfileImageResponse>.toModel() = map { it.toModel() }