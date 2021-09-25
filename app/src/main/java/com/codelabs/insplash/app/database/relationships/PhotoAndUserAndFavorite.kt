package com.codelabs.insplash.app.database.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.codelabs.insplash.app.database.entities.FavoriteEntity
import com.codelabs.insplash.app.database.entities.PhotoEntity
import com.codelabs.insplash.app.database.entities.UserEntity
import com.codelabs.insplash.app.database.entities.toModel
import com.codelabs.insplash.app.models.Photo

data class PhotoAndUserAndFavorite(
    @Embedded val photo: PhotoEntity,

    @Relation(parentColumn = "username_ref", entityColumn = "username")
    val user: UserEntity?,

    @Relation(parentColumn = "id", entityColumn = "favorite_photo_id")
    val favorite: FavoriteEntity?
)

fun PhotoAndUserAndFavorite.toModel(): Photo {
    return Photo(
        id = photo.id,
        color = photo.color,
        blurHash = photo.blurHash,
        downloads = photo.downloads,
        likes = photo.likes,
        urls = photo.urls?.toModel(),
        user = user?.toModel(),
        isFavorite = favorite?.favoritePhotoId != null
    )
}

fun List<PhotoAndUserAndFavorite>.toModel() = map { it.toModel() }
