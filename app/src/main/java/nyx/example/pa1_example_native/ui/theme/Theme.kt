package nyx.example.pa1_example_native.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = darkBlue,
    primaryVariant = darkBlue,
    secondary = lightBlue
)

private val LightColorPalette = lightColors(
    primary = darkBlue,
    primaryVariant = darkBlue,
    secondary = Yellow

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
fun Pa1_example_nativeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors =/* if (darkTheme) {
        DarkColorPalette
    } else {
    */
        LightColorPalette
  //  }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}