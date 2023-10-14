package com.thermondo.androidchallenge.features.core.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.thermondo.androidchallenge.features.core.domain.model.Fairings

@JsonClass(generateAdapter = true)
data class FairingsResponse(
    @Json(name = "recovered")
    val recovered: Boolean?,
    @Json(name = "recovery_attempt")
    val recoveryAttempt: Boolean?,
    @Json(name = "reused")
    val reused: Boolean?,
    @Json(name = "ships")
    val ships: List<String>
) {
    fun toFairings(): Fairings {
        return Fairings(
            recovered = recovered,
            recoveryAttempt = recoveryAttempt,
            reused = reused,
            ships = ships
        )
    }
}