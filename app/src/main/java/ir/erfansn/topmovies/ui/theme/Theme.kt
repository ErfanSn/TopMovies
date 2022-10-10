package ir.erfansn.topmovies.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme()

private val LightColorScheme = lightColorScheme()

@Composable
fun TopMoviesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colorScheme = if (darkTheme) {
        if (dynamicColor) dynamicDarkColorScheme(context) else DarkColorScheme
    } else {
        if (dynamicColor) dynamicLightColorScheme(context) else LightColorScheme
    }
    val systemUiController = rememberSystemUiController()
    systemUiController.setNavigationBarColor(
        color = colorScheme.surfaceColorAtElevation(NavigationBarDefaults.Elevation),
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
