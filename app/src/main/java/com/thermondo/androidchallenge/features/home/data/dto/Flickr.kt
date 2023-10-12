package com.thermondo.androidchallenge.features.home.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Flickr(
    @Json(name = "original")
    val original: List<String>,
    @Json(name = "small")
    val small: List<Any>
)