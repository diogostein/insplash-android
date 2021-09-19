package com.codelabs.insplash.app.api.responses

import com.codelabs.insplash.app.models.User
import com.squareup.moshi.Json

data class UserResponse(
    @field:Json(name = "username") val username: String?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "bio") val bio: String?,
    @field:Json(name = "location") val location: String?,
    @field:Json(name = "profile_image") val profileImage: ProfileImageResponse?,
)

fun UserResponse.toModel(): User {
    return User(
        username = username,
        name = name,
        bio = bio,
        location = location,
        profileImage = profileImage?.toModel(),
    )
}

fun List<UserResponse>.toModel() = map { it.toModel() }