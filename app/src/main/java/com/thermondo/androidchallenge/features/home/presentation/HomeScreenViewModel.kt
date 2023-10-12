package com.thermondo.androidchallenge.features.home.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thermondo.androidchallenge.features.home.domain.usecase.GetAllLaunchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getAllLaunchesUseCase: GetAllLaunchesUseCase
) : ViewModel() {

    var response: MutableState<String> = mutableStateOf("")
        private set

    fun onButtonPressed() {
        viewModelScope.launch {
            getAllLaunchesUseCase().collect {
                response.value = it.data?.joinToString("\n") ?: ""
            }
        }
    }
}