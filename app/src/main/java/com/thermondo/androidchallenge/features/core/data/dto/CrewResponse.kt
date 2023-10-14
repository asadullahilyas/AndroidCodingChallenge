package com.thermondo.androidchallenge.features.core.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.thermondo.androidchallenge.features.core.domain.model.Crew

@JsonClass(generateAdapter = true)
data class CrewResponse(
    @Json(name = "crew")
    val crew: String,
    @Json(name = "role")
    val role: String
) {
    fun toCrew(): Crew {
        return Crew(
            crew = crew,
            role = role
        )
    }
}