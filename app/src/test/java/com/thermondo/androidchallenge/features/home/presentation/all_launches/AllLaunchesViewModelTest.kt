package com.thermondo.androidchallenge.features.home.presentation.all_launches

import com.thermondo.androidchallenge.common.isNeitherNullNorEmptyNorBlank
import com.thermondo.androidchallenge.common.isNullOrEmptyOrBlank
import com.thermondo.androidchallenge.features.home.domain.usecase.GetAllBookmarkedLaunchesUseCase
import com.thermondo.androidchallenge.features.home.domain.usecase.GetAllLaunchesUseCase
import com.thermondo.androidchallenge.features.home.domain.usecase.ToggleBookmarkUseCase
import com.thermondo.androidchallenge.repo.FakeLaunchesRepo
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AllLaunchesViewModelTest {

    private lateinit var viewModel: AllLaunchesViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestCoroutineScope(testDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        val launchesRepo = FakeLaunchesRepo()
        viewModel = AllLaunchesViewModel(
            GetAllLaunchesUseCase(launchesRepo),
            ToggleBookmarkUseCase(launchesRepo),
            GetAllBookmarkedLaunchesUseCase(launchesRepo),
        )
        viewModel.coroutineDispatcher = testDispatcher
        testScope.runBlockingTest { viewModel.onUserEvent(AllLaunchesUserEvent.LoadAllLaunches) }
    }

    @Test
    fun `check if response parsing is working, should return true for expected array size 3`() {
        val launches = viewModel.allLaunchesToDisplay.toList()
        assertEquals(3, launches.size)
    }

    @Test
    fun `toggle bookmark and check if its bookmark flag is true`() = testScope.runBlockingTest {
        viewModel.onUserEvent(AllLaunchesUserEvent.ToggleBookmark(
            viewModel.allLaunchesToDisplay.first()
        ))
        assertTrue(viewModel.allLaunchesToDisplay[0].isBookmarked)
        assertFalse(viewModel.allLaunchesToDisplay[1].isBookmarked)
        assertFalse(viewModel.allLaunchesToDisplay[2].isBookmarked)
    }

    @Test
    fun `double toggle bookmark and check if its bookmark flag is false`() = testScope.runBlockingTest {
        viewModel.onUserEvent(AllLaunchesUserEvent.ToggleBookmark(
            viewModel.allLaunchesToDisplay.first()
        ))
        assertTrue(viewModel.allLaunchesToDisplay[0].isBookmarked)
        viewModel.onUserEvent(AllLaunchesUserEvent.ToggleBookmark(
            viewModel.allLaunchesToDisplay.first()
        ))
        assertFalse(viewModel.allLaunchesToDisplay[0].isBookmarked)
        assertFalse(viewModel.allLaunchesToDisplay[1].isBookmarked)
        assertFalse(viewModel.allLaunchesToDisplay[2].isBookmarked)
    }

    @Test
    fun `check if sorting with flickr links working, first element should have flickr links`() {
        val launches = viewModel.allLaunchesToDisplay.toList()
        assertTrue(launches[0].imageUrl.isNeitherNullNorEmptyNorBlank())
        assertTrue(launches[1].imageUrl.isNullOrEmptyOrBlank())
        assertTrue(launches[2].imageUrl.isNullOrEmptyOrBlank())
    }
}