package com.thermondo.androidchallenge.features.home.domain.repo

import com.thermondo.androidchallenge.common.Response
import com.thermondo.androidchallenge.features.core.domain.model.Launch

interface LaunchesRepo {
    suspend fun getAllLaunches(): Response<List<Launch>>
    suspend fun getAllBookmarkedLaunches(): List<Launch>

    suspend fun addToBookmark(launch: Launch)

    suspend fun removeFromBookmark(launch: Launch)
}