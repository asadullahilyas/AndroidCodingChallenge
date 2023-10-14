package com.thermondo.androidchallenge.features.home.presentation.bookmarked_launches

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BookmarkedLaunchesScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: BookmarkedLaunchesViewModel = hiltViewModel()

    LaunchedEffect(key1 = Unit) {

    }
}