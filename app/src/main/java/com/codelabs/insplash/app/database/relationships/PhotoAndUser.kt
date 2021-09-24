package com.codelabs.insplash.app.database.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.codelabs.insplash.app.database.entities.PhotoEntity
import com.codelabs.insplash.app.database.entities.UserEntity
import com.codelabs.insplash.app.database.entities.toModel
import com.codelabs.insplash.app.models.Photo

data class PhotoAndUser(
    @Embedded val photo: PhotoEntity,

    @Relation(parentColumn = "username_ref", entityColumn = "username")
    val user: UserEntity?
)


fun PhotoAndUser.toModel(): Photo {
    return Photo(
        id = photo.id,
        color = photo.color,
        blurHash = photo.blurHash,
        downloads = photo.downloads,
        likes = photo.likes,
        urls = photo.urls?.toModel(),
        user = user?.toModel()
    )
}
