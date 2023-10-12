package com.thermondo.androidchallenge.features.home.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Crew(
    @Json(name = "crew")
    val crew: String,
    @Json(name = "role")
    val role: String
)