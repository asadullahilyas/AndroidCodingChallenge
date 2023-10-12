package com.thermondo.androidchallenge.features.home.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LaunchResponse(
    @Json(name = "auto_update")
    val autoUpdate: Boolean,
    @Json(name = "capsules")
    val capsules: List<String>,
    @Json(name = "cores")
    val cores: List<Core>,
    @Json(name = "crew")
    val crew: List<Crew>,
    @Json(name = "date_local")
    val dateLocal: String,
    @Json(name = "date_precision")
    val datePrecision: String,
    @Json(name = "date_unix")
    val dateUnix: Int,
    @Json(name = "date_utc")
    val dateUtc: String,
    @Json(name = "details")
    val details: String?,
    @Json(name = "failures")
    val failures: List<Failure>,
    @Json(name = "fairings")
    val fairings: Fairings?,
    @Json(name = "flight_number")
    val flightNumber: Int,
    @Json(name = "id")
    val id: String,
    @Json(name = "launch_library_id")
    val launchLibraryId: String?,
    @Json(name = "launchpad")
    val launchpad: String,
    @Json(name = "links")
    val links: Links,
    @Json(name = "name")
    val name: String,
    @Json(name = "net")
    val net: Boolean,
    @Json(name = "payloads")
    val payloads: List<String>,
    @Json(name = "rocket")
    val rocket: String,
    @Json(name = "ships")
    val ships: List<String>,
    @Json(name = "static_fire_date_unix")
    val staticFireDateUnix: Int?,
    @Json(name = "static_fire_date_utc")
    val staticFireDateUtc: String?,
    @Json(name = "success")
    val success: Boolean?,
    @Json(name = "tbd")
    val tbd: Boolean,
    @Json(name = "upcoming")
    val upcoming: Boolean,
    @Json(name = "window")
    val window: Int?
)