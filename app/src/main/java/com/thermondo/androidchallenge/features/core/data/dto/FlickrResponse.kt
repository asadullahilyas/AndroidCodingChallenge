package com.thermondo.androidchallenge.features.core.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.thermondo.androidchallenge.features.core.domain.model.Flickr

@JsonClass(generateAdapter = true)
data class FlickrResponse(
    @Json(name = "original")
    val original: List<String>,
    @Json(name = "small")
    val small: List<String>
) {
    fun toFlickr(): Flickr {
        return Flickr(
            original = original,
            small = small
        )
    }
}