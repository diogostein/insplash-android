package com.codelabs.insplash.app.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.codelabs.insplash.app.models.ProfileImage

@Entity
data class ProfileImageEntity(
    @ColumnInfo(name = "small") val small: String?,
    @ColumnInfo(name = "medium") val medium: String?,
    @ColumnInfo(name = "large") val large: String?,
)

fun ProfileImageEntity.toModel(): ProfileImage {
    return ProfileImage(
        small = small,
        medium = medium,
        large = large
    )
}
