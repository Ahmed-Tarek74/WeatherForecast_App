package com.get_ready.weather_app.di

import com.get_ready.data.dataSources.local.LastSearchedCityDataSource
import com.get_ready.data.dataSources.local.LastSearchedCityDataSourceImpl
import com.get_ready.data.dataSources.remote.SearchCityRemoteDataSource
import com.get_ready.data.dataSources.remote.SearchCityRemoteDataSourceImpl
import com.get_ready.data.dataSources.remote.WeatherDataSource
import com.get_ready.data.dataSources.remote.WeatherDataSourceImpl
import com.get_ready.data.local.CityPreferencesManager
import com.get_ready.data.local.CityPreferencesManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindWeatherDataSource(weatherDataSourceImpl: WeatherDataSourceImpl): WeatherDataSource

    @Binds
    @Singleton
    abstract fun bindLastSearchedCityDataSource(lastSearchedCityDataSourceImpl: LastSearchedCityDataSourceImpl): LastSearchedCityDataSource

    @Binds
    @Singleton
    abstract fun bindSearchCityDataSource(searchCityDataSourceImpl: SearchCityRemoteDataSourceImpl): SearchCityRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindCityPreferencesManager(cityPreferencesManager: CityPreferencesManagerImpl): CityPreferencesManager
}