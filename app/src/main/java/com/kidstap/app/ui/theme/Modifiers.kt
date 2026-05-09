package com.kidstap.app.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// A better way to do Neo-brutalism Shadow is to just use standard shadow modifier with no blur, 
// or wrap the composable, or use drawBehind with translate.
// Here is a composed modifier that offsets the content and draws the shadow.
fun Modifier.neoBrutalismShadow(
    shadowColor: Color = KtInk,
    cornerRadius: Dp = 28.dp,
    shadowOffsetY: Dp = 6.dp
) = this.drawBehind {
    drawRoundRect(
        color = shadowColor,
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadius.toPx()),
        topLeft = androidx.compose.ui.geometry.Offset(0f, shadowOffsetY.toPx()),
        size = size
    )
}

/**
 * Common modifier for neo-brutalism cards.
 */
fun Modifier.kidsTapCard(
    backgroundColor: Color = KtCream,
    borderColor: Color = KtInk,
    cornerRadius: Dp = 28.dp,
    borderWidth: Dp = 3.dp,
    shadowOffsetY: Dp = 6.dp
) = this
    .neoBrutalismShadow(shadowColor = borderColor, cornerRadius = cornerRadius, shadowOffsetY = shadowOffsetY)
    // The shadow goes down, so we don't offset the content itself here, the shadow is drawn below.
    // To ensure the shadow is visible, the parent container might need bottom padding.
    .background(backgroundColor, RoundedCornerShape(cornerRadius))
    .border(borderWidth, borderColor, RoundedCornerShape(cornerRadius))
    .clip(RoundedCornerShape(cornerRadius))

/**
 * Modifier for interactive buttons. It reacts to clicks by removing the shadow and shifting down.
 */
@Composable
fun Modifier.kidsTapButton(
    onClick: () -> Unit,
    backgroundColor: Color = KtYellow,
    borderColor: Color = KtInk,
    cornerRadius: Dp = 20.dp,
    borderWidth: Dp = 3.dp,
    shadowOffsetY: Dp = 5.dp
): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    
    val currentOffsetY = if (isPressed) shadowOffsetY else 0.dp
    val shadowVisibleHeight = if (isPressed) 0.dp else shadowOffsetY

    return this
        .offset(y = currentOffsetY)
        .neoBrutalismShadow(
            shadowColor = borderColor, 
            cornerRadius = cornerRadius, 
            shadowOffsetY = shadowVisibleHeight
        )
        .background(backgroundColor, RoundedCornerShape(cornerRadius))
        .border(borderWidth, borderColor, RoundedCornerShape(cornerRadius))
        .clip(RoundedCornerShape(cornerRadius))
        .clickable(
            interactionSource = interactionSource,
            indication = null, // Disable default ripple
            onClick = onClick
        )
}
