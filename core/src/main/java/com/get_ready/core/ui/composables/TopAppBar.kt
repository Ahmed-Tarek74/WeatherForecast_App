package com.get_ready.core.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.get_ready.core.ui.theme.Dimensions.SPACER_WIDTH

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, navigationIcon: @Composable () -> Unit = {}) {
    TopAppBar(
        colors = topAppBarColors(),
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(SPACER_WIDTH)
            )
        },
        navigationIcon = navigationIcon
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topAppBarColors()= TopAppBarColors(
    containerColor = colorScheme.secondaryContainer,
    scrolledContainerColor = colorScheme.secondaryContainer,
    navigationIconContentColor = colorScheme.onSecondaryContainer,
    titleContentColor = colorScheme.onSecondaryContainer,
    actionIconContentColor = colorScheme.onSecondaryContainer
)
