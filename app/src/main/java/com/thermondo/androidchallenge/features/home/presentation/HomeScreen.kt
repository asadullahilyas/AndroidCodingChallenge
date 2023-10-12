package com.thermondo.androidchallenge.features.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(navigator: DestinationsNavigator) {

    val viewModel: HomeScreenViewModel = hiltViewModel()

    Column {
        Button(
            modifier = Modifier.align(alignment = CenterHorizontally),
            onClick = {
            viewModel.onButtonPressed()
        }) {
            Text(text = "Press Me")
        }
        Text(text = viewModel.response.value)
    }
}