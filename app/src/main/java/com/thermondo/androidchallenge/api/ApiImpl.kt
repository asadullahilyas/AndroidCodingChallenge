package com.thermondo.androidchallenge.api

import com.thermondo.androidchallenge.api.response.ApiResponse
import com.thermondo.androidchallenge.features.home.data.dto.LaunchResponse
import javax.inject.Inject

class ApiImpl @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun getAllLaunches(): ApiResponse<List<LaunchResponse>> {
        return ApiResponse(apiInterface.getAllLaunches())
    }
}