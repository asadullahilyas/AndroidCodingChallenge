package com.thermondo.androidchallenge.features.core.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.thermondo.androidchallenge.features.core.domain.model.Patch

@JsonClass(generateAdapter = true)
data class PatchResponse(
    @Json(name = "large")
    val large: String?,
    @Json(name = "small")
    val small: String?
) {
    fun toPatch(): Patch {
        return Patch(
            large = large,
            small = small
        )
    }
}