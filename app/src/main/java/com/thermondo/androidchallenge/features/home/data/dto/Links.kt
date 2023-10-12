package com.thermondo.androidchallenge.features.home.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Links(
    @Json(name = "article")
    val article: String?,
    @Json(name = "flickr")
    val flickr: Flickr?,
    @Json(name = "patch")
    val patch: Patch?,
    @Json(name = "presskit")
    val presskit: String?,
    @Json(name = "reddit")
    val reddit: Reddit?,
    @Json(name = "webcast")
    val webcast: String?,
    @Json(name = "wikipedia")
    val wikipedia: String?,
    @Json(name = "youtube_id")
    val youtubeId: String?
)