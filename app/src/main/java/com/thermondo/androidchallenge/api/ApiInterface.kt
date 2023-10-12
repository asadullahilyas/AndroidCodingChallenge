package com.thermondo.androidchallenge.api

import com.thermondo.androidchallenge.features.home.data.dto.LaunchResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @GET("v5/launches")
    suspend fun getAllLaunches(): Response<List<LaunchResponse>>
}