package com.thermondo.androidchallenge.features.home.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Fairings(
    @Json(name = "recovered")
    val recovered: Boolean?,
    @Json(name = "recovery_attempt")
    val recoveryAttempt: Boolean?,
    @Json(name = "reused")
    val reused: Boolean?,
    @Json(name = "ships")
    val ships: List<String>
)