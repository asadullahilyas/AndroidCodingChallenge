package com.thermondo.androidchallenge.features.home.domain.di

import com.thermondo.androidchallenge.api.ApiImpl
import com.thermondo.androidchallenge.common.Settings
import com.thermondo.androidchallenge.features.home.data.repo.LaunchesRepoImpl
import com.thermondo.androidchallenge.features.home.domain.repo.LaunchesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object LaunchesModule {

    @Provides
    @ViewModelScoped
    fun getLaunchesRepo(apiImpl: ApiImpl, settings: Settings): LaunchesRepo {
        return LaunchesRepoImpl(apiImpl, settings)
    }
}