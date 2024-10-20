package com.get_ready.core.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color


val Black = Color(0xFF000000)
val White = Color(0xFFFFFFFF)
val LightGray = Color(0xFFE9EAEC)
val Gray = Color(0xFFAEB6BF)
val DarkGray = Color(0xff65737e)
val Pink = Color(0xffefd0d4)
val lightRed = Color(0xfff26b73)
val Red = Color(0xffe02020)
val DarkRed = Color(0xff8c253f)
val LightBlue = Color(0xFF9FC3E9)
val primaryDarkBlue = Color(0xFF253649)
val Blue = Color(0xFF2196f3)
val Green = Color(0xFF008000)
val LightGreen = Color(0xFFAFE1AF)
val Yellow = Color(0xFFFFFF00)
val LightYellow = Color(0xFFFFFFC5)
val Orange = Color(0xFFfbc02d)
// colors to be used in compose like this MaterialTheme.colors.-----
//Grayscale Colors
val ColorScheme.black: Color
    get() = Black
val ColorScheme.white: Color
    get() = White
val ColorScheme.gray: Color
    get() = Gray
val ColorScheme.light_gray: Color
    get() = LightGray
val ColorScheme.dark_gray: Color
    get() = DarkGray
// Blue Shades
val ColorScheme.light_blue: Color
    get() = LightBlue
val ColorScheme.blue: Color
    get() = Blue
val ColorScheme.primary_dark_blue: Color
    get() = primaryDarkBlue
// Red Shades
val ColorScheme.red: Color
    get() = Red
val ColorScheme.light_red: Color
    get() = lightRed
val ColorScheme.dark_red: Color
    get() = DarkRed
val ColorScheme.pink: Color
    get() = Pink

// Yellow Shades
val ColorScheme.yellow: Color
    get() = Yellow
val ColorScheme.light_yellow: Color
    get() = LightYellow
// green Shades
val ColorScheme.green: Color
    get() = Green
val ColorScheme.light_green: Color
    get() = LightGreen