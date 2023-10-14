package com.thermondo.androidchallenge.features.home.domain.usecase

import com.thermondo.androidchallenge.features.core.domain.model.Launch
import com.thermondo.androidchallenge.features.home.domain.repo.LaunchesRepo
import javax.inject.Inject

class ToggleBookmarkUseCase @Inject constructor(
    private val launchesRepo: LaunchesRepo
) {
    suspend operator fun invoke(launch: Launch, shouldBookmark: Boolean) {
        if (shouldBookmark) {
            launchesRepo.addToBookmark(launch)
        } else {
            launchesRepo.removeFromBookmark(launch)
        }
    }
}