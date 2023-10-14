package com.thermondo.androidchallenge.features.core.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.thermondo.androidchallenge.features.core.domain.model.Reddit

@JsonClass(generateAdapter = true)
data class RedditResponse(
    @Json(name = "campaign")
    val campaign: String?,
    @Json(name = "launch")
    val launch: String?,
    @Json(name = "media")
    val media: String?,
    @Json(name = "recovery")
    val recovery: String?
) {
    fun toReddit(): Reddit {
        return Reddit(
            campaign = campaign,
            launch = launch,
            media = media,
            recovery = recovery
        )
    }
}