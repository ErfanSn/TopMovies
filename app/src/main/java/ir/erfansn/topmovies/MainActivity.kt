package ir.erfansn.topmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ir.erfansn.topmovies.features.home.HomeScreen
import ir.erfansn.topmovies.ui.theme.TopMoviesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopMoviesTheme {
                HomeScreen()
            }
        }
    }
}
