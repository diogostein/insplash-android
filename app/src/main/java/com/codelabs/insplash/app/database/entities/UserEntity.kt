package com.codelabs.insplash.app.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codelabs.insplash.app.models.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "location") val location: String?,
    @Embedded(prefix = "profile_image_") val profileImage: ProfileImageEntity?
)

fun UserEntity.toModel(): User {
    return User(
        username = username,
        name = name,
        location = location,
        profileImage = profileImage?.toModel(),
    )
}
