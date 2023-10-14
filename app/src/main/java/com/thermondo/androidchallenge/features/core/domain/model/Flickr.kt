package com.thermondo.androidchallenge.features.core.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Flickr(
    val original: List<String>,
    val small: List<String>
)