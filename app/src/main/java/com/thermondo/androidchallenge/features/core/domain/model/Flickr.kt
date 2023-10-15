package com.thermondo.androidchallenge.features.core.domain.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Flickr(
    val original: List<String>,
    val small: List<String>
) : Parcelable