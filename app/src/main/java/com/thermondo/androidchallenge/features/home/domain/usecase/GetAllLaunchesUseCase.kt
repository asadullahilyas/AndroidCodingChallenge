package com.thermondo.androidchallenge.features.home.domain.usecase

import com.thermondo.androidchallenge.common.Response
import com.thermondo.androidchallenge.features.home.data.dto.LaunchResponse
import com.thermondo.androidchallenge.features.home.domain.repo.HomeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllLaunchesUseCase @Inject constructor(
    private val homeRepo: HomeRepo
) {
    operator fun invoke(): Flow<Response<List<LaunchResponse>>> {
        return flow {
            emit(Response.Loading())
            emit(homeRepo.getAllLaunches())
        }
    }
}