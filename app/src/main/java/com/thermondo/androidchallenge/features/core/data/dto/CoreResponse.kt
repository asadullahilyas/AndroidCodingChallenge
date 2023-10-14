package com.thermondo.androidchallenge.features.core.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.thermondo.androidchallenge.features.core.domain.model.Core

@JsonClass(generateAdapter = true)
data class CoreResponse(
    @Json(name = "core")
    val core: String?,
    @Json(name = "flight")
    val flight: Int?,
    @Json(name = "gridfins")
    val gridfins: Boolean?,
    @Json(name = "landing_attempt")
    val landingAttempt: Boolean?,
    @Json(name = "landing_success")
    val landingSuccess: Boolean?,
    @Json(name = "landing_type")
    val landingType: String?,
    @Json(name = "landpad")
    val landpad: String?,
    @Json(name = "legs")
    val legs: Boolean?,
    @Json(name = "reused")
    val reused: Boolean?
) {
    fun toCore(): Core {
        return Core(
            core = core,
            flight = flight,
            gridfins = gridfins,
            landingAttempt = landingAttempt,
            landingSuccess = landingSuccess,
            landingType = landingType,
            landpad = landpad,
            legs = legs,
            reused = reused
        )
    }
}