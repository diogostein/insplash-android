package com.codelabs.insplash.app.api.responses

import com.squareup.moshi.Json

data class PagingResponse<T>(
    @field:Json(name = "total") val total: Int,
    @field:Json(name = "total_pages") val totalPages: Int,
    @field:Json(name = "results") val results: List<T>,
)