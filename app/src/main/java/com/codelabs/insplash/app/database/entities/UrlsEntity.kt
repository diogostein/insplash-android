package com.codelabs.insplash.app.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.codelabs.insplash.app.models.PhotoUrls

@Entity
data class UrlsEntity(
    @ColumnInfo(name = "raw") val raw: String?,
    @ColumnInfo(name = "full") val full: String?,
    @ColumnInfo(name = "regular") val regular: String?,
    @ColumnInfo(name = "small") val small: String?,
    @ColumnInfo(name = "thumb") val thumb: String?,
)

fun UrlsEntity.toModel(): PhotoUrls {
    return PhotoUrls(
        raw = raw,
        full = full,
        regular = regular,
        small = small,
        thumb = thumb
    )
}
