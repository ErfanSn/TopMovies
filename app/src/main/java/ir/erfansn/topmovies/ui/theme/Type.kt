package ir.erfansn.topmovies.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle

private val defaultTypography = Typography()

val Typography = Typography(
    titleMedium = defaultTypography.titleMedium.removeFontPadding(),
    titleLarge = defaultTypography.titleLarge.removeFontPadding(),
    labelLarge = defaultTypography.labelLarge.removeFontPadding(),
    labelSmall = defaultTypography.labelSmall.removeFontPadding(),
)

private fun TextStyle.removeFontPadding() = merge(
    TextStyle(
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )
)
