package com.thermondo.androidchallenge.features.detail.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thermondo.androidchallenge.features.core.domain.model.Launch
import com.thermondo.androidchallenge.features.home.domain.usecase.GetAllBookmarkedLaunchesUseCase
import com.thermondo.androidchallenge.features.home.domain.usecase.ToggleBookmarkUseCase
import com.thermondo.androidchallenge.features.home.presentation.all_launches.LaunchItemToDisplay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchDetailsViewModel @Inject constructor(
    private val getAllBookmarkedLaunchesUseCase: GetAllBookmarkedLaunchesUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase
) : ViewModel() {

    var launchDate = mutableStateOf("")
        private set

    var isBookmarked = mutableStateOf(false)
        private set

    fun initiate(launch: Launch) {
        launchDate.value = LaunchItemToDisplay.getReadableDate(launch)

        viewModelScope.launch {
            getAllBookmarkedLaunchesUseCase().collect { allBookmarkedLaunches ->
                isBookmarked.value = allBookmarkedLaunches.any { bookmarkedLaunch ->
                    bookmarkedLaunch.id == launch.id
                }
            }
        }
    }

    fun onUserEvent(userEvent: LaunchDetailsUserEvent) {
        when (userEvent) {
            is LaunchDetailsUserEvent.ToggleBookmarkLaunch -> toggleBookmarkLaunch(userEvent.launch)
        }
    }

    private fun toggleBookmarkLaunch(launch: Launch) {
        viewModelScope.launch {
            toggleBookmarkUseCase(launch, isBookmarked.value.not())
        }
    }
}

sealed interface LaunchDetailsUserEvent {
    data class ToggleBookmarkLaunch(val launch: Launch) : LaunchDetailsUserEvent
}