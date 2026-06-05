package com.kidstap.app.domain.model

import androidx.compose.ui.graphics.Color

enum class ShapeType {
    CIRCLE,
    SQUARE,
    TRIANGLE,
    STAR,
    HEART;

    companion object {
        fun random() = entries.random()
    }
}

// Shape colors - bright, accessible palette for kids
object ShapeColors {
    val colors = listOf(
        Color(0xFFEF4444), // red
        Color(0xFF3B82F6), // blue
        Color(0xFFFCD34D), // yellow
        Color(0xFF10B981), // green
        Color(0xFFA855F7), // purple
        Color(0xFFEC4899), // pink
    )

    fun random() = colors.random()
    fun getGradient(color: Color): Pair<Color, Color> {
        // Lighter version for gradient bottom
        return color to color.copy(alpha = 0.6f)
    }
}
