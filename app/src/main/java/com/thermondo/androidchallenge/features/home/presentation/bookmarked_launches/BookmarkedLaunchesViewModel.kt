package com.thermondo.androidchallenge.features.home.presentation.bookmarked_launches

import androidx.lifecycle.ViewModel
import com.thermondo.androidchallenge.common.Settings
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkedLaunchesViewModel @Inject constructor(
    private val settings: Settings
) : ViewModel() {

    fun loadBookmarkedLaunches() {

    }
}