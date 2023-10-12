package com.thermondo.androidchallenge.features.home.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Failure(
    @Json(name = "altitude")
    val altitude: Int?,
    @Json(name = "reason")
    val reason: String,
    @Json(name = "time")
    val time: Int
)