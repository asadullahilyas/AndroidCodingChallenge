package com.thermondo.androidchallenge.features.core.domain.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Launch(
    val autoUpdate: Boolean,
    val capsules: List<String>,
    val cores: List<Core>,
    val crew: List<Crew>,
    val dateLocal: String,
    val datePrecision: String,
    val dateUnix: Int,
    val dateUtc: String,
    val details: String?,
    val failures: List<Failure>,
    val fairings: Fairings?,
    val flightNumber: Int,
    val id: String,
    val launchLibraryId: String?,
    val launchpad: String,
    val links: Links,
    val name: String,
    val net: Boolean,
    val payloads: List<String>,
    val rocket: String,
    val ships: List<String>,
    val staticFireDateUnix: Int?,
    val staticFireDateUtc: String?,
    val success: Boolean?,
    val tbd: Boolean,
    val upcoming: Boolean,
    val window: Int?
) : Parcelable