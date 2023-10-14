package com.thermondo.androidchallenge.features.core.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Failure(
    val altitude: Int?,
    val reason: String,
    val time: Int
)