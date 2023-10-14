package com.thermondo.androidchallenge.features.core.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Fairings(
    val recovered: Boolean?,
    val recoveryAttempt: Boolean?,
    val reused: Boolean?,
    val ships: List<String>
)