package com.gmail.orlandroyd.jetreddit.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
    primary = RwPrimary,
    primaryVariant = RwPrimaryDark,
    secondary = Color.LightGray,
    secondaryVariant = RwPrimaryDark,
    error = Red800
)

private val DarkThemeColors = darkColors(
    primary = RwPrimaryDark,
    primaryVariant = RwPrimary,
    secondary = Color.Black,
    error = Red800
)

@Composable
fun JetRedditTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (JetRedditThemeSettings.isInDarkTheme.value) DarkThemeColors else LightThemeColors,
        content = content
    )
}


object JetRedditThemeSettings {
    var isInDarkTheme: MutableState<Boolean> = mutableStateOf(false)
}