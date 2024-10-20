package com.get_ready.weather_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.get_ready.cityinput.composableScreens.SearchCityInputScreen
import com.get_ready.cityinput.viewModel.InputCityViewModel
import com.get_ready.features.ScreenRoute
import com.get_ready.currentweather.composableScreens.CurrentWeatherScreen
import com.get_ready.currentweather.viewModels.CurrentWeatherViewModel
import com.get_ready.dailyforecasts.composableScreens.DailyForecastScreen
import com.get_ready.dailyforecasts.viewModels.DailyForecastsViewModel
import com.get_ready.features.ScreenRoute.CityInputScreen
import com.get_ready.features.ScreenRoute.CurrentWeatherScreen
import com.get_ready.features.ScreenRoute.DailyForecastScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: ScreenRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<CityInputScreen> {
            val viewModel: InputCityViewModel = hiltViewModel()
            SearchCityInputScreen(
                viewModel.viewState.collectAsStateWithLifecycle(),
                citySaveEvent = viewModel.citySaveEvent,
                setIntent= viewModel::setIntent
            ) { cityName ->
                navController.navigate(CurrentWeatherScreen(cityName))
            }
        }
        composable<CurrentWeatherScreen> {
            val viewModel: CurrentWeatherViewModel = hiltViewModel()
            CurrentWeatherScreen(
                viewModel.viewState.collectAsStateWithLifecycle(),
                onEditCurrentCity = { navController.navigate(CityInputScreen) }
            ) { cityName ->
                navController.navigate(DailyForecastScreen(cityName))
            }
        }
        composable<DailyForecastScreen>
        {
            val viewModel: DailyForecastsViewModel = hiltViewModel()
            DailyForecastScreen(viewModel.viewState.collectAsStateWithLifecycle()) { navController.popBackStack() }
        }
    }
}