package com.get_ready.weather_app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.get_ready.core.ui.theme.Weather_AppTheme
import com.get_ready.weather_app.ui.navigation.AppNavGraph
import com.get_ready.weather_app.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val startDestination by viewModel.startDestination.collectAsStateWithLifecycle()
            Weather_AppTheme {
                AppNavGraph(startDestination = startDestination)
            }
        }
    }
}

