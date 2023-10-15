package com.thermondo.androidchallenge.features.core.domain.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Links(
    val article: String?,
    val flickr: Flickr?,
    val patch: Patch?,
    val presskit: String?,
    val reddit: Reddit?,
    val webcast: String?,
    val wikipedia: String?,
    val youtubeId: String?
) : Parcelable