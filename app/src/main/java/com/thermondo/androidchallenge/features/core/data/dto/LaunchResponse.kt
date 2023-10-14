package com.thermondo.androidchallenge.features.core.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.thermondo.androidchallenge.features.core.domain.model.Launch

@JsonClass(generateAdapter = true)
data class LaunchResponse(
    @Json(name = "auto_update")
    val autoUpdate: Boolean,
    @Json(name = "capsules")
    val capsules: List<String>,
    @Json(name = "cores")
    val coreResponses: List<CoreResponse>,
    @Json(name = "crew")
    val crewResponses: List<CrewResponse>,
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
    val failureResponses: List<FailureResponse>,
    @Json(name = "fairings")
    val fairingsResponse: FairingsResponse?,
    @Json(name = "flight_number")
    val flightNumber: Int,
    @Json(name = "id")
    val id: String,
    @Json(name = "launch_library_id")
    val launchLibraryId: String?,
    @Json(name = "launchpad")
    val launchpad: String,
    @Json(name = "links")
    val linksResponse: LinksResponse,
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
) {
    fun toLaunch(): Launch {
        return Launch(
            autoUpdate = autoUpdate,
            capsules = capsules,
            cores = coreResponses.map { it.toCore() },
            crew = crewResponses.map { it.toCrew() },
            dateLocal = dateLocal,
            datePrecision = datePrecision,
            dateUnix = dateUnix,
            dateUtc = dateUtc,
            details = details,
            failures = failureResponses.map { it.toFailure() },
            fairings = fairingsResponse?.toFairings(),
            flightNumber = flightNumber,
            id = id,
            launchLibraryId = launchLibraryId,
            launchpad = launchpad,
            links = linksResponse.toLinks(),
            name = name,
            net = net,
            payloads = payloads,
            rocket = rocket,
            ships = ships,
            staticFireDateUnix = staticFireDateUnix,
            staticFireDateUtc = staticFireDateUtc,
            success = success,
            tbd = tbd,
            upcoming = upcoming,
            window = window
        )
    }
}