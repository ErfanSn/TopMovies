package ir.erfansn.topmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.core.view.WindowCompat
import ir.erfansn.topmovies.features.home.HomeScreen
import ir.erfansn.topmovies.ui.theme.TopMoviesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            TopMoviesTheme {
                Surface(contentColor = MaterialTheme.colorScheme.background) {
                    HomeScreen()
                }
            }
        }
    }
}
