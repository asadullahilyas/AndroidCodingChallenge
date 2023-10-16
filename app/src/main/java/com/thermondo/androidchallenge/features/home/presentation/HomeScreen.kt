package com.thermondo.androidchallenge.features.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thermondo.androidchallenge.features.home.presentation.all_launches.AllLaunchesScreen
import com.thermondo.androidchallenge.features.home.presentation.bookmarked_launches.BookmarkedLaunchesScreen
import com.thermondo.androidchallenge.ui.theme.ContrastingColor
import com.thermondo.androidchallenge.ui.theme.ThemeBackgroundColor
import com.thermondo.androidchallenge.ui.theme.ThemeColor

/**
 * HomeScreen hosts the bottom tab bar and two composable screens. One for showing all launches
 * and one for showing all the bookmarked launches. One thing to note here is that, since it is a
 * single screen, pressing back button was finishing this screen and taking user back to Application
 * Drawer of Android. But I wanted to provide a user experience where if user is on bookmarked
 * launches screen and he/she presses back, app brings them back to all launches screen. So I am
 * achieving that by introducing a back pressed callback in my BookmarkedLaunchesScreen.
 */
@Destination
@Composable
fun HomeScreen(navigator: DestinationsNavigator) {

    val viewModel: HomeScreenViewModel = hiltViewModel()

    val homeScreenOption by viewModel.homeScreenOption

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ThemeBackgroundColor)
    ) {
        when (homeScreenOption) {
            HomeScreenOption.AllLaunches -> AllLaunchesScreen(navigator = navigator)
            HomeScreenOption.BookmarkedLaunches -> BookmarkedLaunchesScreen(navigator = navigator) {
                viewModel.onUserEvent(HomeScreenUserEvent.OnBackPressed)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(BottomCenter)
                .padding(bottom = 8.dp)
                .padding(8.dp)
                .border(width = 1.dp, shape = RoundedCornerShape(20.dp), color = ThemeColor)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(ThemeBackgroundColor.copy(alpha = 0.9F))
                .padding(8.dp)
        ) {
            IconButton(
                modifier = Modifier
                    .weight(1.0F)
                    .align(CenterVertically),
                onClick = {
                    viewModel.onUserEvent(HomeScreenUserEvent.GoToAllLaunches)
                }) {
                Column(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "Show list of launches",
                        tint = when (homeScreenOption) {
                            HomeScreenOption.AllLaunches -> ContrastingColor
                            HomeScreenOption.BookmarkedLaunches -> Color.White
                        }
                    )
                    Text(
                        text = "All Launches",
                        fontSize = 10.sp,
                        color = when (homeScreenOption) {
                            HomeScreenOption.AllLaunches -> ContrastingColor
                            HomeScreenOption.BookmarkedLaunches -> Color.White
                        }
                    )
                }
            }
            IconButton(
                modifier = Modifier
                    .weight(1.0F)
                    .align(CenterVertically),
                onClick = {
                    viewModel.onUserEvent(HomeScreenUserEvent.GoToBookmarkedLaunches)
                }) {
                Column(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Show list of Bookmarked Launches",
                        tint = when (homeScreenOption) {
                            HomeScreenOption.AllLaunches -> Color.White
                            HomeScreenOption.BookmarkedLaunches -> ContrastingColor
                        }
                    )
                    Text(
                        text = "Bookmarked Launches",
                        fontSize = 10.sp,
                        color = when (homeScreenOption) {
                            HomeScreenOption.AllLaunches -> Color.White
                            HomeScreenOption.BookmarkedLaunches -> ContrastingColor
                        }
                    )
                }
            }
        }
    }
}