package com.thermondo.androidchallenge.features.core.domain.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Fairings(
    val recovered: Boolean?,
    val recoveryAttempt: Boolean?,
    val reused: Boolean?,
    val ships: List<String>
) : Parcelable