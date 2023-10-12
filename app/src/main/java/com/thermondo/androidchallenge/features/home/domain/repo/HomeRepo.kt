package com.thermondo.androidchallenge.features.home.domain.repo

import com.thermondo.androidchallenge.common.Response
import com.thermondo.androidchallenge.features.home.data.dto.LaunchResponse

interface HomeRepo {
    suspend fun getAllLaunches(): Response<List<LaunchResponse>>
}