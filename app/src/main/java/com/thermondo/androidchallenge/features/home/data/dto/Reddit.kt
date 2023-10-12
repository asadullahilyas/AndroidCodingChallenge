package com.thermondo.androidchallenge.features.home.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Reddit(
    @Json(name = "campaign")
    val campaign: String?,
    @Json(name = "launch")
    val launch: String?,
    @Json(name = "media")
    val media: String?,
    @Json(name = "recovery")
    val recovery: String?
)