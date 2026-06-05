package com.kidstap.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.GenericFontFamily

// Note: To use the exact "Fredoka" and "Space Grotesk" fonts,
// please add the .ttf files to `res/font/` and map them here using `Font(R.font.fredoka_bold)`,
// or use Google Fonts provider `androidx.compose.ui.text.googlefonts.GoogleFont`.
// For now, we are using default sans-serif fallbacks to prevent compilation errors.

val FredokaFallback = FontFamily.SansSerif
val SpaceGroteskFallback = FontFamily.Monospace

val KidsTapTypography = Typography(
    // ------------------------------------
    // Headings (Fredoka)
    // ------------------------------------
    displayLarge = TextStyle(
        fontFamily = FredokaFallback,
        fontWeight = FontWeight.Bold,
        fontSize = 88.sp,
        letterSpacing = (-0.02).sp,
        lineHeight = 88.sp
    ),
    displayMedium = TextStyle(
        fontFamily = FredokaFallback,
        fontWeight = FontWeight.Bold,
        fontSize = 56.sp,
        letterSpacing = (-0.02).sp
    ),
    displaySmall = TextStyle(
        fontFamily = FredokaFallback,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        letterSpacing = (-0.01).sp
    ),
    headlineLarge = TextStyle(
        fontFamily = FredokaFallback,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FredokaFallback,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = FredokaFallback,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    // ------------------------------------
    // UI Text & Secondary (Space Grotesk)
    // ------------------------------------
    bodyLarge = TextStyle(
        fontFamily = SpaceGroteskFallback,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = SpaceGroteskFallback,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = SpaceGroteskFallback,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = SpaceGroteskFallback,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle( // Used for "crumbs" / small uppercase labels
        fontFamily = SpaceGroteskFallback,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        letterSpacing = 1.6.sp // 0.16em roughly
    ),
    labelSmall = TextStyle(
        fontFamily = SpaceGroteskFallback,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        letterSpacing = 1.4.sp
    )
)
