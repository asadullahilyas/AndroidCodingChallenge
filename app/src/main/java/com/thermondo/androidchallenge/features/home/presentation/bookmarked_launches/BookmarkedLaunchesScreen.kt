package com.thermondo.androidchallenge.features.home.presentation.bookmarked_launches

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.thermondo.androidchallenge.common.LaunchItem

@Composable
fun BookmarkedLaunchesScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: BookmarkedLaunchesViewModel = hiltViewModel()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadBookmarkedLaunches()
    }

    val bookmarkedLaunchesToDisplay by viewModel.allBookmarkedLaunchesToDisplay.collectAsStateWithLifecycle()

    Box(
        modifier = modifier.then(
            Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    ) {
        if (bookmarkedLaunchesToDisplay.isEmpty()) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "No bookmarked items found",
                color = Color.Gray
            )
        } else {
            val totalColumns = if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 3

            LazyVerticalGrid(
                modifier = modifier,
                columns = GridCells.Fixed(totalColumns),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)

            ) {
                items(bookmarkedLaunchesToDisplay.size) { index ->
                    val launchItem = bookmarkedLaunchesToDisplay[index]
                    LaunchItem(
                        launch = launchItem,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        onBookmarkClicked = {
                            viewModel.onUserEvent(
                                BookmarkedUserEvent.RemoveFromBookmarks(launchItem)
                            )
                        }
                    )
                }
                for (i in 0 until totalColumns) {
                    item {
                        Box(modifier = Modifier.height(100.dp))
                    }
                }
            }
        }
    }
}