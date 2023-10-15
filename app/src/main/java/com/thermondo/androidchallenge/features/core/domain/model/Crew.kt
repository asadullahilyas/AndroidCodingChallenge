package com.thermondo.androidchallenge.features.core.domain.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Crew(
    val crew: String,
    val role: String
) : Parcelable