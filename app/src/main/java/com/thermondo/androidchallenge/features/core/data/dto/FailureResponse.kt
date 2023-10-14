package com.thermondo.androidchallenge.features.core.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.thermondo.androidchallenge.features.core.domain.model.Failure

@JsonClass(generateAdapter = true)
data class FailureResponse(
    @Json(name = "altitude")
    val altitude: Int?,
    @Json(name = "reason")
    val reason: String,
    @Json(name = "time")
    val time: Int
) {
    fun toFailure(): Failure {
        return Failure(
            altitude = altitude,
            reason = reason,
            time = time
        )
    }
}