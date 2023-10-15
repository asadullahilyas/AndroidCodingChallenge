package com.thermondo.androidchallenge.features.detail.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thermondo.androidchallenge.R
import com.thermondo.androidchallenge.features.core.domain.model.Launch
import com.thermondo.androidchallenge.ui.theme.ContrastingColor
import com.thermondo.androidchallenge.ui.theme.ThemeBackgroundColor
import com.thermondo.androidchallenge.ui.theme.ThemeColor

@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Destination
@Composable
fun LaunchDetailsScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    launch: Launch
) {
    val viewModel: LaunchDetailsViewModel = hiltViewModel()

    val isBookmarked by viewModel.isBookmarked
    val launchDate by viewModel.launchDate

    LaunchedEffect(key1 = Unit) {
        viewModel.initiate(launch)
    }

    Box(
        modifier = modifier.then(Modifier.background(ThemeBackgroundColor))
    ) {

        Column(
            modifier = Modifier.verticalScroll(state = rememberScrollState())
        ) {

            val images = launch.links.flickr?.original ?: emptyList()
            val pagerState = rememberPagerState(pageCount = { images.size })

            Box {
                HorizontalPager(state = pagerState) { page ->
                    GlideImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.0F),
                        model = images[page],
                        contentScale = ContentScale.Crop,
                        contentDescription = "Image number $page",
                        loading = placeholder(R.drawable.ic_rocket_placeholder), // https://cdn3.iconfinder.com/data/icons/business-solid/128/18-Launching-1024.png
                        failure = placeholder(R.drawable.ic_rocket_placeholder),
                        transition = CrossFade
                    ) {
                        it.skipMemoryCache(false)
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(images.size) { iteration ->
                            val color =
                                if (pagerState.currentPage == iteration) Color.White else Color.DarkGray
                            Box(
                                modifier = Modifier
                                    .padding(2.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                    .size(10.dp)

                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(ThemeBackgroundColor.copy(alpha = 0.8F)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(1.0F)
                                .padding(horizontal = 20.dp),
                            text = launch.name.uppercase(),
                            color = ContrastingColor,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        IconButton(
                            onClick = {
                                viewModel.onUserEvent(
                                    LaunchDetailsUserEvent.ToggleBookmarkLaunch(
                                        launch
                                    )
                                )
                            }
                        ) {
                            Icon(
                                imageVector = if (isBookmarked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Bookmark icon",
                                tint = Color.White
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = launchDate,
                fontSize = 20.sp,
                color = ContrastingColor
            )

            Spacer(modifier = Modifier.height(12.dp))

            launch.details?.let {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = it,
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }

        IconButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 8.dp, start = 8.dp),
            onClick = { navigator.popBackStack() }) {
            Icon(
                modifier = Modifier
                    .background(ThemeColor, shape = CircleShape)
                    .padding(4.dp),
                imageVector = Icons.Default.ArrowBack, contentDescription = "Back button",
                tint = Color.White
            )
        }
    }
}