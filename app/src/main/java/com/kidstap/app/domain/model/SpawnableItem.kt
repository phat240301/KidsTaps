package com.kidstap.app.domain.model

import androidx.compose.ui.geometry.Offset
import java.util.UUID

sealed class SpawnableItem(
    open val id: String = UUID.randomUUID().toString(),
    open val centerX: Float,
    open val centerY: Float,
    open val sizePixels: Float,
) {
    data class Shape(
        val type: ShapeType,
        val color: androidx.compose.ui.graphics.Color,
        override val id: String = UUID.randomUUID().toString(),
        override val centerX: Float,
        override val centerY: Float,
        override val sizePixels: Float = 100f,
    ) : SpawnableItem(id, centerX, centerY, sizePixels)

    data class Animal(
        val type: AnimalType,
        override val id: String = UUID.randomUUID().toString(),
        override val centerX: Float,
        override val centerY: Float,
        override val sizePixels: Float = 120f,
    ) : SpawnableItem(id, centerX, centerY, sizePixels)

    fun getBounds(): androidx.compose.ui.geometry.Rect {
        val halfSize = sizePixels / 2f
        return androidx.compose.ui.geometry.Rect(
            left = centerX - halfSize,
            top = centerY - halfSize,
            right = centerX + halfSize,
            bottom = centerY + halfSize,
        )
    }

    fun isHit(tapX: Float, tapY: Float): Boolean {
        val bounds = getBounds()
        return tapX >= bounds.left && tapX <= bounds.right &&
               tapY >= bounds.top && tapY <= bounds.bottom
    }
}
