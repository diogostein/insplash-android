package com.codelabs.insplash.app.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class PhotoEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "username_ref") val username: String?,
    @ColumnInfo(name = "color") val color: String?,
    @ColumnInfo(name = "blur_hash") val blurHash: String?,
    @ColumnInfo(name = "downloads") val downloads: Int?,
    @ColumnInfo(name = "likes") val likes: Int?,
    @Embedded(prefix = "url_") val urls: UrlsEntity?,
)
