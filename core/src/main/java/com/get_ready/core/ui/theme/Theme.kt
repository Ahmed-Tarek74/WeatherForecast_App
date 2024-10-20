package com.get_ready.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = primaryDarkBlue,
    onPrimary = LightBlue,
    primaryContainer = LightBlue,
    onPrimaryContainer = primaryDarkBlue,
    secondary = primaryDarkBlue,
    onSecondary = LightGray,
    secondaryContainer = DarkGray,
    onSecondaryContainer = LightGray,
    background = primaryDarkBlue,
    surface = primaryDarkBlue,
    onBackground = LightGray,
    onSurface = LightGray,
    errorContainer = Pink,
    onErrorContainer = Red,
    error = lightRed
)

private val LightColorScheme = lightColorScheme(
    primary = LightBlue,
    onPrimary = primaryDarkBlue,
    primaryContainer = LightGray,
    onPrimaryContainer = primaryDarkBlue,
    secondary = LightGray,
    onSecondary = DarkGray,
    secondaryContainer = LightGray,
    onSecondaryContainer = primaryDarkBlue,
    background = LightBlue,
    surface = LightBlue,
    onBackground = primaryDarkBlue,
    onSurface = primaryDarkBlue,
    errorContainer = Pink,
    onErrorContainer = Red,
    error = lightRed
)

@Composable
fun Weather_AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = getTypography(),
        content = content
    )
}