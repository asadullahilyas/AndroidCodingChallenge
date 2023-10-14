package com.thermondo.androidchallenge.features.core.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.thermondo.androidchallenge.features.core.domain.model.Links

@JsonClass(generateAdapter = true)
data class LinksResponse(
    @Json(name = "article")
    val article: String?,
    @Json(name = "flickr")
    val flickrResponse: FlickrResponse?,
    @Json(name = "patch")
    val patchResponse: PatchResponse?,
    @Json(name = "presskit")
    val presskit: String?,
    @Json(name = "reddit")
    val redditResponse: RedditResponse?,
    @Json(name = "webcast")
    val webcast: String?,
    @Json(name = "wikipedia")
    val wikipedia: String?,
    @Json(name = "youtube_id")
    val youtubeId: String?
) {
    fun toLinks(): Links {
        return Links(
            article = article,
            flickr = flickrResponse?.toFlickr(),
            patch = patchResponse?.toPatch(),
            presskit = presskit,
            reddit = redditResponse?.toReddit(),
            webcast = webcast,
            wikipedia = wikipedia,
            youtubeId = youtubeId
        )
    }
}