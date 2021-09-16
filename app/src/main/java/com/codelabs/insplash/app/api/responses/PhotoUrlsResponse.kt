package com.codelabs.insplash.app.api.responses

import com.codelabs.insplash.app.models.PhotoUrls
import com.squareup.moshi.Json

data class PhotoUrlsResponse(
    @field:Json(name = "raw") val raw: String?,
    @field:Json(name = "full") val full: String?,
    @field:Json(name = "regular") val regular: String?,
    @field:Json(name = "small") val small: String?,
    @field:Json(name = "thumb") val thumb: String?,
)

fun PhotoUrlsResponse.toModel(): PhotoUrls {
    return PhotoUrls(
        raw = raw,
        full = full,
        regular = regular,
        small = small,
        thumb = thumb
    )
}
