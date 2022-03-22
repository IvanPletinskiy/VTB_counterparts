package com.handen.vtb_counterparts.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.betterlifeapps.std.ui.theme.UiTypography

private val DarkColorPalette = darkColors(
    primary = Color(0xFF2639F2),
    primaryVariant = Color(0xFF2639F2),
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Color(0xFF2639F2),
    primaryVariant = Color(0xFF2639F2),
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun VTB_counterpartsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = UiTypography,
        shapes = Shapes,
        content = content
    )
}