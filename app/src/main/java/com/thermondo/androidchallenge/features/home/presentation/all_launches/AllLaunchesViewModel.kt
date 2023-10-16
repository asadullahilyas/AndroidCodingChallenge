package com.thermondo.androidchallenge.features.home.presentation.all_launches

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thermondo.androidchallenge.common.Response
import com.thermondo.androidchallenge.common.format
import com.thermondo.androidchallenge.common.isNullOrEmptyOrBlank
import com.thermondo.androidchallenge.features.core.domain.model.Launch
import com.thermondo.androidchallenge.features.destinations.LaunchDetailsScreenDestination
import com.thermondo.androidchallenge.features.home.domain.usecase.GetAllBookmarkedLaunchesUseCase
import com.thermondo.androidchallenge.features.home.domain.usecase.GetAllLaunchesUseCase
import com.thermondo.androidchallenge.features.home.domain.usecase.ToggleBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import javax.inject.Inject

@HiltViewModel
class AllLaunchesViewModel @Inject constructor(
    private val allLaunchesUseCase: GetAllLaunchesUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
    private val bookmarkedLaunchesUseCase: GetAllBookmarkedLaunchesUseCase
) : ViewModel() {

    var coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default

    private val allLaunches = mutableListOf<Launch>()

    var isLoading = mutableStateOf(true)
        private set

    var error = mutableStateOf("")
        private set

    var allLaunchesToDisplay = mutableStateListOf<LaunchItemToDisplay>()
        private set

    fun onUserEvent(userEvent: AllLaunchesUserEvent) {
        when (userEvent) {
            AllLaunchesUserEvent.LoadAllLaunches -> loadAllLaunches()
            is AllLaunchesUserEvent.ToggleBookmark -> toggleBookmark(userEvent.launch)
            is AllLaunchesUserEvent.OpenDetails -> openDetails(userEvent.navigator, userEvent.launch)
        }
    }

    private fun loadAllLaunches() {
        viewModelScope.launch(coroutineDispatcher) {
            allLaunchesUseCase().collect {
                when (it) {
                    is Response.Error -> updateUiOnError(it.message ?: "Unknown error occurred.")
                    is Response.Loading -> updateUiOnLoading()
                    is Response.Success -> {
                        allLaunches.clear()
                        allLaunches.addAll(it.data ?: emptyList())
                        val bookmarkedLaunches = bookmarkedLaunchesUseCase().first()
                        updateUiOnSuccess(
                            it
                                .data
                                ?.map { launchItem ->
                                    LaunchItemToDisplay(
                                        launchItem.id,
                                        launchItem.links.flickr?.original?.firstOrNull(),
                                        LaunchItemToDisplay.getReadableDate(launchItem).uppercase(),
                                        launchItem.name.uppercase(),
                                        isBookmarked = bookmarkedLaunches.any { bookmarkedLaunch -> bookmarkedLaunch.id == launchItem.id }
                                    )
                                }
                                ?.sortedBy { launchItemToDisplay -> launchItemToDisplay.imageUrl.isNullOrEmptyOrBlank() }
                                ?: emptyList()
                        )
                    }
                }
            }
        }
    }

    private fun toggleBookmark(launchItemToDisplay: LaunchItemToDisplay) {
        val launch = allLaunches.find { it.id == launchItemToDisplay.id } ?: return
        viewModelScope.launch(coroutineDispatcher) {
            toggleBookmarkUseCase(launch, launchItemToDisplay.isBookmarked.not())
            allLaunchesToDisplay.set(
                allLaunchesToDisplay.indexOfFirst { it == launchItemToDisplay },
                launchItemToDisplay.copy(isBookmarked = launchItemToDisplay.isBookmarked.not())
            )
        }
    }

    private fun openDetails(navigator: DestinationsNavigator, launchItemToDisplay: LaunchItemToDisplay) {
        val launch = allLaunches.find { it.id == launchItemToDisplay.id } ?: return
        navigator.navigate(LaunchDetailsScreenDestination(launch))
    }

    private fun updateUiOnError(errorMessage: String) {
        isLoading.value = false
        error.value = errorMessage
        allLaunchesToDisplay.clear()
    }

    private fun updateUiOnLoading() {
        isLoading.value = true
        error.value = ""
        allLaunchesToDisplay.clear()
    }

    private fun updateUiOnSuccess(launches: List<LaunchItemToDisplay>) {
        isLoading.value = false
        error.value = ""
        allLaunchesToDisplay.addAll(launches)
    }
}

sealed interface AllLaunchesUserEvent {
    data object LoadAllLaunches : AllLaunchesUserEvent
    data class ToggleBookmark(val launch: LaunchItemToDisplay): AllLaunchesUserEvent
    data class OpenDetails(val navigator: DestinationsNavigator, val launch: LaunchItemToDisplay) : AllLaunchesUserEvent
}

data class LaunchItemToDisplay(
    val id: String,
    val imageUrl: String? = null,
    val date: String,
    val title: String,
    val isBookmarked: Boolean = false
) {
    companion object {
        fun getReadableDate(launch: Launch): String {
            val launchDateTime = DateTime((launch.dateUnix * 1000).toLong())
            return when (launch.datePrecision) {
                "month" -> launchDateTime.format("MMM, YYYY")
                "day" -> launchDateTime.format("MMM dd, YYYY")
                "hour" -> launchDateTime.format("HH:00 - MMM dd, YYYY")
                else -> launch.dateUtc
            }
        }
    }
}