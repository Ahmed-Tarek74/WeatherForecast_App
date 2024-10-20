package com.get_ready.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.get_ready.core.R

var FontFamily.Companion.BOLD: FontFamily
    get() = FontFamily(Font(R.font.barlow_bold))
    set(_) = Unit

var FontFamily.Companion.Medium: FontFamily
    get() = FontFamily(Font(R.font.barlow_medium))
    set(_) = Unit

var FontFamily.Companion.Regular: FontFamily
    get() = FontFamily(Font(R.font.barlow_regular))
    set(_) = Unit


var FontFamily.Companion.SEMI_BOLD: FontFamily
    get() = FontFamily(Font(R.font.barlow_semibold))
    set(_) = Unit

// Set of Material typography styles to start with
@Composable
fun getTypography() = Typography(
    headlineLarge = TextStyle(
        fontSize = 32.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        fontFamily = FontFamily.BOLD
    ),
    headlineMedium = TextStyle(
        fontSize = 24.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.5.sp,
        fontFamily = FontFamily.BOLD
    ),
    headlineSmall = TextStyle(
        fontSize = 12.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.5.sp,
        fontFamily = FontFamily.BOLD
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Medium,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Medium,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Medium,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)