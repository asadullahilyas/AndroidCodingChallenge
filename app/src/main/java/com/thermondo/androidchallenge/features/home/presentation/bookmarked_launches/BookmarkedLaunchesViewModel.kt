package com.thermondo.androidchallenge.features.home.presentation.bookmarked_launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thermondo.androidchallenge.features.core.domain.model.Launch
import com.thermondo.androidchallenge.features.home.domain.usecase.GetAllBookmarkedLaunchesUseCase
import com.thermondo.androidchallenge.features.home.domain.usecase.ToggleBookmarkUseCase
import com.thermondo.androidchallenge.features.home.presentation.all_launches.LaunchItemToDisplay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkedLaunchesViewModel @Inject constructor(
    private val getAllBookmarkedLaunchesUseCase: GetAllBookmarkedLaunchesUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase
) : ViewModel() {

    private val allBookmarkedLaunches = mutableListOf<Launch>()

    private val _allBookmarkedLaunchesToDisplay: MutableStateFlow<List<LaunchItemToDisplay>> = MutableStateFlow(emptyList())
    val allBookmarkedLaunchesToDisplay: StateFlow<List<LaunchItemToDisplay>> = _allBookmarkedLaunchesToDisplay

    fun loadBookmarkedLaunches() {
        viewModelScope.launch {
            getAllBookmarkedLaunchesUseCase().collect { bookmarkedLaunches ->

                allBookmarkedLaunches.clear()
                allBookmarkedLaunches.addAll(bookmarkedLaunches)

                _allBookmarkedLaunchesToDisplay.value = bookmarkedLaunches.map {
                    LaunchItemToDisplay(
                        it.id,
                        it.links.flickr?.original?.firstOrNull(),
                        LaunchItemToDisplay.getReadableDate(it) ?: "N/A",
                        it.name,
                        true
                    )
                }
            }
        }
    }

    fun onUserEvent(userEvent: BookmarkedUserEvent) {
        when (userEvent) {
            is BookmarkedUserEvent.RemoveFromBookmarks -> removeFromBookmarks(userEvent.launchItemToDisplay)
        }
    }

    private fun removeFromBookmarks(launchItemToDisplay: LaunchItemToDisplay) {
        val launch = allBookmarkedLaunches.find { it.id == launchItemToDisplay.id } ?: return
        viewModelScope.launch {
            toggleBookmarkUseCase(launch, false)
        }
    }
}

sealed interface BookmarkedUserEvent {
    data class RemoveFromBookmarks(val launchItemToDisplay: LaunchItemToDisplay) : BookmarkedUserEvent
}