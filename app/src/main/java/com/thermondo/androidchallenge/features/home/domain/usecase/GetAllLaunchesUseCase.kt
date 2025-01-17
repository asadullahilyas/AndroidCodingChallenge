package com.thermondo.androidchallenge.features.home.domain.usecase

import com.thermondo.androidchallenge.common.Response
import com.thermondo.androidchallenge.features.core.domain.model.Launch
import com.thermondo.androidchallenge.features.home.domain.repo.LaunchesRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllLaunchesUseCase @Inject constructor(
    private val launchesRepo: LaunchesRepo
) {
    operator fun invoke(): Flow<Response<List<Launch>>> {
        return flow {
            emit(Response.Loading())
            emit(launchesRepo.getAllLaunches())
        }
    }
}