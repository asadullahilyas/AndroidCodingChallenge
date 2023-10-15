package com.thermondo.androidchallenge.features.home.presentation.all_launches

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.thermondo.androidchallenge.common.LaunchItem
import com.thermondo.androidchallenge.common.isNeitherNullNorEmptyNorBlank

@Composable
fun AllLaunchesScreen(modifier: Modifier = Modifier) {

    val viewModel: AllLaunchesViewModel = hiltViewModel()

    val isLoading by viewModel.isLoading
    val error by viewModel.error
    val allLaunches = viewModel.allLaunchesToDisplay

    LaunchedEffect(key1 = Unit) {
        viewModel.onUserEvent(AllLaunchesUserEvent.LoadAllLaunches)
    }

    Box(
        modifier = modifier
            .then(
                Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            )
    ) {

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .align(Alignment.Center)
            )
        } else if (error.isNeitherNullNorEmptyNorBlank()) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = error,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        } else {

            if (allLaunches.isEmpty()) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "No data was available",
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
                    items(allLaunches.size) { index ->
                        val launchItem = allLaunches[index]
                        LaunchItem(
                            launch = launchItem,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            onBookmarkClicked = {
                                viewModel.onUserEvent(
                                    AllLaunchesUserEvent.ToggleBookmark(launchItem)
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
}