package com.kidstap.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Immutable
data class KidsTapColors(
    val red: Color,
    val yellow: Color,
    val blue: Color,
    val green: Color,
    val cream: Color,
    val soft: Color,
    val ink: Color
)

val LocalKidsTapColors = staticCompositionLocalOf {
    KidsTapColors(
        red = Color.Unspecified,
        yellow = Color.Unspecified,
        blue = Color.Unspecified,
        green = Color.Unspecified,
        cream = Color.Unspecified,
        soft = Color.Unspecified,
        ink = Color.Unspecified
    )
}

// Since KidsTap UI doesn't explicitly rely on Material3 components for its primary styling,
// we mostly use our custom colors. However, we still provide a light color scheme
// for underlying Material components if used.
private val LightColorScheme = lightColorScheme(
    primary = KtYellow,
    onPrimary = KtInk,
    secondary = KtBlue,
    onSecondary = KtCream,
    error = KtRed,
    onError = KtCream,
    background = KtCream,
    onBackground = KtInk,
    surface = KtCream,
    onSurface = KtInk
)

@Composable
fun KidsTapTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Always light theme for this app usually, but keeping param
    content: @Composable () -> Unit
) {
    val kidsTapColors = KidsTapColors(
        red = KtRed,
        yellow = KtYellow,
        blue = KtBlue,
        green = KtGreen,
        cream = KtCream,
        soft = KtSoft,
        ink = KtInk
    )

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = kidsTapColors.cream.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }

    CompositionLocalProvider(LocalKidsTapColors provides kidsTapColors) {
        MaterialTheme(
            colorScheme = LightColorScheme,
            typography = KidsTapTypography,
            content = content
        )
    }
}

// Helper object to easily access KidsTap theme properties
object KidsTapTheme {
    val colors: KidsTapColors
        @Composable
        get() = LocalKidsTapColors.current
    val typography: androidx.compose.material3.Typography
        @Composable
        get() = MaterialTheme.typography
}
