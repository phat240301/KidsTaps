package com.kidstap.app.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kidstap.app.ui.theme.KidsTapTheme
import com.kidstap.app.ui.theme.KtBlue
import com.kidstap.app.ui.theme.KtCream
import com.kidstap.app.ui.theme.KtGreen
import com.kidstap.app.ui.theme.KtInk
import com.kidstap.app.ui.theme.KtRed
import com.kidstap.app.ui.theme.KtSoft
import com.kidstap.app.ui.theme.KtYellow
import com.kidstap.app.ui.theme.kidsTapButton
import com.kidstap.app.ui.theme.kidsTapCard
import com.kidstap.app.ui.theme.neoBrutalismShadow

enum class ButtonStyle(val backgroundColor: Color, val textColor: Color) {
    Primary(KtYellow, KtInk),
    Red(KtRed, KtCream),
    Blue(KtBlue, KtCream),
    Cream(KtCream, KtInk)
}

@Composable
fun KidsTapButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: ButtonStyle = ButtonStyle.Primary,
    textStyle: TextStyle = KidsTapTheme.typography.headlineSmall,
    contentPadding: PaddingValues = PaddingValues(horizontal = 28.dp, vertical = 16.dp)
) {
    Box(
        modifier = modifier
            .kidsTapButton(
                onClick = onClick,
                backgroundColor = style.backgroundColor,
                borderColor = KtInk
            )
            .padding(contentPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = textStyle,
            color = style.textColor
        )
    }
}

@Composable
fun KidsTapPill(
    text: String,
    modifier: Modifier = Modifier,
    isDark: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val bgColor = if (isDark) KtInk else KtCream
    val contentColor = if (isDark) KtCream else KtInk

    var boxModifier = modifier
        .background(bgColor, CircleShape)
        .border(2.dp, KtInk, CircleShape)
        
    if (onClick != null) {
        boxModifier = boxModifier
            .clip(CircleShape)
            .clickable { onClick() }
    }
    
    Box(
        modifier = boxModifier.padding(horizontal = 16.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text.uppercase(),
            style = KidsTapTheme.typography.labelMedium,
            color = contentColor
        )
    }
}

@Composable
fun KidsTapCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = KtCream,
    contentPadding: PaddingValues = PaddingValues(24.dp),
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .kidsTapCard(backgroundColor = backgroundColor)
            .padding(contentPadding)
    ) {
        content()
    }
}

@Composable
fun KidsTapSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val bgColor = if (checked) KtGreen else KtSoft
    val targetOffset = if (checked) 20.dp else 0.dp
    
    val offset by animateFloatAsState(
        targetValue = targetOffset.value,
        animationSpec = tween(150),
        label = "switch_offset"
    )

    Box(
        modifier = modifier
            .size(width = 44.dp, height = 24.dp)
            .background(bgColor, CircleShape)
            .border(2.dp, KtInk, CircleShape)
            .clip(CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onCheckedChange(!checked) }
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .padding(start = 2.dp)
                .offset(x = offset.dp)
                .size(16.dp)
                .background(KtCream, CircleShape)
                .border(2.dp, KtInk, CircleShape)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KidsTapSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    modifier: Modifier = Modifier
) {
    Slider(
        value = value,
        onValueChange = onValueChange,
        valueRange = valueRange,
        steps = steps,
        modifier = modifier.height(28.dp),
        colors = SliderDefaults.colors(
            thumbColor = Color.Transparent,
            activeTrackColor = Color.Transparent,
            inactiveTrackColor = Color.Transparent,
        ),
        thumb = {
            // Custom Neo-Brutalism Thumb
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .neoBrutalismShadow(KtInk, cornerRadius = 14.dp, shadowOffsetY = 3.dp)
                    .background(KtYellow, CircleShape)
                    .border(3.dp, KtInk, CircleShape)
            )
        },
        track = { sliderPositions ->
            // Custom Track
            val fraction = (value - valueRange.start) / (valueRange.endInclusive - valueRange.start)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(14.dp)
                    .background(KtSoft, CircleShape)
                    .border(2.dp, KtInk, CircleShape)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction)
                        .height(14.dp)
                        .background(KtBlue, CircleShape)
                        .border(2.dp, KtInk, CircleShape)
                )
            }
        }
    )
}
