package com.thermondo.androidchallenge.features.splash.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thermondo.androidchallenge.R
import com.thermondo.androidchallenge.features.destinations.HomeScreenDestination
import com.thermondo.androidchallenge.ui.theme.ThemeColor

@RootNavGraph(start = true)
@Destination
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator
) {
    Box(
        modifier = modifier.background(ThemeColor)
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.launch_animation))
        val state = animateLottieCompositionAsState(composition = composition)

        LottieAnimation(
            modifier = Modifier.fillMaxSize(),
            composition = composition,
            progress = {
                println(state.progress)
                state.progress
           },
        )

        if (state.progress >= 1.0F) {
            LaunchedEffect(key1 = Unit) {
                navigator.popBackStack()
                navigator.navigate(HomeScreenDestination)
            }
        }
    }
}