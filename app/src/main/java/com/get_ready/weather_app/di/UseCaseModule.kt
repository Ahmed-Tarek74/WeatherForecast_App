package com.get_ready.weather_app.di

import com.get_ready.domain.repo.LastSearchedCityRepository
import com.get_ready.domain.repo.SearchCityRepository
import com.get_ready.domain.repo.WeatherRepository
import com.get_ready.domain.usecases.GetDailyForecastUseCase
import com.get_ready.domain.usecases.GetCurrentWeatherUseCase
import com.get_ready.domain.usecases.GetLastSearchedCityUseCase
import com.get_ready.domain.usecases.SaveLastSearchedCityUseCase
import com.get_ready.domain.usecases.SearchCityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    @ViewModelScoped
    fun provideGetCurrentWeatherUseCase(weatherRepository: WeatherRepository): GetCurrentWeatherUseCase =
        GetCurrentWeatherUseCase(weatherRepository)

    @Provides
    @ViewModelScoped
    fun provideGetDailyForecastsUseCase(weatherRepository: WeatherRepository): GetDailyForecastUseCase =
        GetDailyForecastUseCase(weatherRepository)

    @Provides
    @ViewModelScoped
    fun provideGetLastSearchedCityUseCase(cityRepository: LastSearchedCityRepository): GetLastSearchedCityUseCase =
        GetLastSearchedCityUseCase(cityRepository)

    @Provides
    @ViewModelScoped
    fun provideSaveLastSearchedCityUseCase(cityRepository: LastSearchedCityRepository): SaveLastSearchedCityUseCase =
        SaveLastSearchedCityUseCase(cityRepository)

    @Provides
    @ViewModelScoped
    fun provideSearchCityUseCase(searchCityRepository: SearchCityRepository): SearchCityUseCase =
        SearchCityUseCase(searchCityRepository)
}