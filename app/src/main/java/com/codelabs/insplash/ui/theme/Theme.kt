package com.codelabs.insplash.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.codelabs.insplash.R

private val DarkColorPalette = darkColors(
    primary = Black,
    primaryVariant = Color(0xFF111111),
    secondary = White,
    onPrimary = White,
    onSecondary = Black,
)

private val LightColorPalette = lightColors(
    primary = White,
    primaryVariant = LightGray,
    secondary = Black,
    onPrimary = Black,
    onSecondary = White,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

private val fonts = FontFamily(
    Font(R.font.pacifico_regular, FontWeight.Normal)
)

private val defaultTypography = Typography()

@Composable
fun InsplashTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography(
            h1 = defaultTypography.h1.copy(fontFamily = fonts),
            h2 = defaultTypography.h2.copy(fontFamily = fonts),
            h3 = defaultTypography.h3.copy(fontFamily = fonts),
            h4 = defaultTypography.h4.copy(fontFamily = fonts),
            h5 = defaultTypography.h5.copy(fontFamily = fonts),
            h6 = defaultTypography.h6.copy(fontFamily = fonts),
            subtitle1 = defaultTypography.subtitle1.copy(fontFamily = fonts),
            subtitle2 = defaultTypography.subtitle2.copy(fontFamily = fonts),
            body1 = defaultTypography.body1.copy(fontFamily = fonts),
            body2 = defaultTypography.body2.copy(fontFamily = fonts),
            button = defaultTypography.button.copy(fontFamily = fonts),
            caption = defaultTypography.caption.copy(fontFamily = fonts),
            overline = defaultTypography.overline.copy(fontFamily = fonts)
        ),
        shapes = Shapes,
        content = content
    )
}