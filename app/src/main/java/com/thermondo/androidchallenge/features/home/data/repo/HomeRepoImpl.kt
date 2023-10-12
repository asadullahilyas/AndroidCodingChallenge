package com.thermondo.androidchallenge.features.home.data.repo

import com.thermondo.androidchallenge.api.ApiImpl
import com.thermondo.androidchallenge.common.Response
import com.thermondo.androidchallenge.features.home.data.dto.LaunchResponse
import com.thermondo.androidchallenge.features.home.domain.repo.HomeRepo
import kotlinx.coroutines.CancellationException
import javax.inject.Inject

class HomeRepoImpl @Inject constructor(
    private val apiImpl: ApiImpl
) : HomeRepo {

    override suspend fun getAllLaunches(): Response<List<LaunchResponse>> {
        return try {
            val response = apiImpl.getAllLaunches()
            if (response.isSuccessful()) {
                Response.Success(response.data)
            } else {
                Response.Error(response.error?.message ?: "Unknown error")
            }
        } catch (e: Exception) {
            if (e is CancellationException) {
                throw e
            }
            Response.Error(e.message ?: "Unknown error")
        }
    }
}