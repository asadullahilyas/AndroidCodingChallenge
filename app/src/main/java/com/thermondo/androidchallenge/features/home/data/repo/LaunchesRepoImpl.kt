package com.thermondo.androidchallenge.features.home.data.repo

import com.thermondo.androidchallenge.api.ApiImpl
import com.thermondo.androidchallenge.common.Response
import com.thermondo.androidchallenge.common.Settings
import com.thermondo.androidchallenge.features.core.domain.model.Launch
import com.thermondo.androidchallenge.features.home.domain.repo.LaunchesRepo
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LaunchesRepoImpl @Inject constructor(
    private val apiImpl: ApiImpl,
    private val settings: Settings
) : LaunchesRepo {

    override suspend fun getAllLaunches(): Response<List<Launch>> {
        return try {
            val response = apiImpl.getAllLaunches()
            if (response.isSuccessful()) {
                Response.Success(response.data?.map { it.toLaunch() })
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

    override suspend fun addToBookmark(launch: Launch) {
        settings.setBookmarkedLaunches(
            listOf(
                *getAllBookmarkedLaunches().first().toTypedArray(), launch
            )
        )
    }

    override suspend fun removeFromBookmark(launch: Launch) {
        val allBookmarks = getAllBookmarkedLaunches().first().toMutableList()
        allBookmarks.removeIf { it.id == launch.id }
        settings.setBookmarkedLaunches(allBookmarks)
    }

    override fun getAllBookmarkedLaunches(): Flow<List<Launch>> {
        return settings.getBookmarkedLaunches()
    }
}