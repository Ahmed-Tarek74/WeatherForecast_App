package com.get_ready.core.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun WeatherConditionIcon(
    iconUrl: String,
    iconDescription: String,
    modifier: Modifier = Modifier,
    iconSize: Dp = 64.dp,
) {
    val backgroundColor = colorScheme.secondaryContainer
    Card(
        shape = CircleShape,
        modifier = modifier.size(iconSize)
    ) {
        AsyncImage(
            model = iconUrl,
            contentDescription = iconDescription,
            modifier = Modifier
                .size(iconSize)
                .background(backgroundColor),
            contentScale = ContentScale.FillBounds
        )
    }
}