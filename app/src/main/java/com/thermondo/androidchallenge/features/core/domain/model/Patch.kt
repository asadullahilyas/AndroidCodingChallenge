package com.thermondo.androidchallenge.features.core.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Patch(
    val large: String?,
    val small: String?
)