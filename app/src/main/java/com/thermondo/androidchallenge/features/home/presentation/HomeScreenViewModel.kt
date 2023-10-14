package com.thermondo.androidchallenge.features.home.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {

    var homeScreenOption: MutableState<HomeScreenOption> = mutableStateOf(HomeScreenOption.AllLaunches)
        private set

    fun onUserEvent(homeScreenUserEvent: HomeScreenUserEvent) {
        when (homeScreenUserEvent) {
            HomeScreenUserEvent.GoToAllLaunches -> homeScreenOption.value = HomeScreenOption.AllLaunches
            HomeScreenUserEvent.GoToBookmarkedLaunches -> homeScreenOption.value = HomeScreenOption.BookmarkedLaunches
        }
    }
}

sealed interface HomeScreenUserEvent {
    data object GoToAllLaunches : HomeScreenUserEvent
    data object GoToBookmarkedLaunches : HomeScreenUserEvent
}

enum class HomeScreenOption {
    AllLaunches,
    BookmarkedLaunches
}