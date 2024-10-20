package com.get_ready.weather_app.di

import com.get_ready.data.repos.LastSearchedCityRepoImpl
import com.get_ready.data.repos.SearchCityRepositoryImpl
import com.get_ready.data.repos.WeatherRepositoryImpl
import com.get_ready.domain.repo.LastSearchedCityRepository
import com.get_ready.domain.repo.SearchCityRepository
import com.get_ready.domain.repo.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {
    @Binds
    @ViewModelScoped
    abstract fun bindWeatherRepo(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository

    @Binds
    @ViewModelScoped
    abstract fun bindLastSearchedCityRepo(lastSearchedCityRepoImpl: LastSearchedCityRepoImpl): LastSearchedCityRepository

    @Binds
    @ViewModelScoped
    abstract fun bindSearchCityRepo(searchCityRepositoryImpl: SearchCityRepositoryImpl): SearchCityRepository
}