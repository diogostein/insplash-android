package com.codelabs.insplash.app.models

data class User(
    val username: String? = null,
    val name: String? = null,
    val bio: String? = null,
    val location: String? = null,
    val profileImage: ProfileImage? = null,
)