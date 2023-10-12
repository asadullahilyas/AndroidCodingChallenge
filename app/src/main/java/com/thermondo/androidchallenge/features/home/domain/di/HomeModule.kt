package com.thermondo.androidchallenge.features.home.domain.di

import com.thermondo.androidchallenge.features.home.data.repo.HomeRepoImpl
import com.thermondo.androidchallenge.api.ApiImpl
import com.thermondo.androidchallenge.features.home.domain.repo.HomeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {

    @Provides
    @ViewModelScoped
    fun getHomeRepo(apiImpl: ApiImpl): HomeRepo {
        return HomeRepoImpl(apiImpl)
    }
}