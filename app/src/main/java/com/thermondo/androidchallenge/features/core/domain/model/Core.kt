package com.thermondo.androidchallenge.features.core.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Core(
    val core: String?,
    val flight: Int?,
    val gridfins: Boolean?,
    val landingAttempt: Boolean?,
    val landingSuccess: Boolean?,
    val landingType: String?,
    val landpad: String?,
    val legs: Boolean?,
    val reused: Boolean?
)