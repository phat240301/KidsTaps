package com.kidstap.app.ui.countdown

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kidstap.app.ui.navigation.Route
import com.kidstap.app.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CountdownScreen(navController: NavController) {
    var countdownNumber by remember { mutableStateOf(3) }

    // Bounce animation for the number
    val scale by animateFloatAsState(
        targetValue = if (countdownNumber % 2 == 0) 0.95f else 1.05f,
        animationSpec = tween(durationMillis = 300),
        label = "bounce"
    )

    LaunchedEffect(Unit) {
        // 3
        delay(1000)
        countdownNumber = 2
        // 2
        delay(1000)
        countdownNumber = 1
        // 1
        delay(1000)
        
        // Navigate to game
        navController.navigate(Route.Game.route) {
            popUpTo(Route.Countdown.route) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(KtBlue),
        contentAlignment = Alignment.Center,
    ) {
        // Background Rings
        DashedRing(size = 600.dp, strokeWidth = 8.dp, dashLength = 30f, gapLength = 20f, color = Color.White.copy(alpha = 0.18f))
        DashedRing(size = 760.dp, strokeWidth = 4.dp, dashLength = 15f, gapLength = 15f, color = Color.White.copy(alpha = 0.1f))

        // Quit Button
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(24.dp)
                .size(56.dp)
                .background(Color.White.copy(alpha = 0.12f), RoundedCornerShape(18.dp))
                .border(2.dp, KtCream, RoundedCornerShape(18.dp))
                .clip(RoundedCornerShape(18.dp))
                .clickable { navController.popBackStack() },
            contentAlignment = Alignment.Center
        ) {
            Text("×", fontSize = 32.sp, color = KtCream, modifier = Modifier.offset(y = (-2).dp))
        }

        // Center Content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Text(
                text = "GET READY",
                style = KidsTapTheme.typography.labelMedium,
                color = KtCream.copy(alpha = 0.85f),
                letterSpacing = 5.sp
            )

            // Number Circle
            Box(
                modifier = Modifier
                    .size(320.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    }
                    .neoBrutalismShadow(Color.Black.copy(alpha = 0.25f), 160.dp, 14.dp)
                    .background(KtYellow, CircleShape)
                    .border(8.dp, KtInk, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = countdownNumber.toString(),
                    fontSize = 240.sp,
                    fontWeight = FontWeight.Bold,
                    color = KtInk,
                    lineHeight = 240.sp,
                    modifier = Modifier.offset(y = (-10).dp)
                )
            }

            // Dots indicator
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Dot(isOn = countdownNumber <= 3)
                Dot(isOn = countdownNumber <= 2)
                Dot(isOn = countdownNumber <= 1)
            }
        }
    }
}

@Composable
private fun DashedRing(size: androidx.compose.ui.unit.Dp, strokeWidth: androidx.compose.ui.unit.Dp, dashLength: Float, gapLength: Float, color: Color) {
    // Rotation animation
    val rotation = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        rotation.animateTo(
            targetValue = 360f,
            animationSpec = androidx.compose.animation.core.infiniteRepeatable(
                animation = tween(20000, easing = LinearEasing)
            )
        )
    }

    Box(
        modifier = Modifier
            .size(size)
            .graphicsLayer { rotationZ = rotation.value }
            .drawBehind {
                drawCircle(
                    color = color,
                    style = Stroke(
                        width = strokeWidth.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashLength, gapLength))
                    )
                )
            }
    )
}

@Composable
private fun Dot(isOn: Boolean) {
    val alpha = if (isOn) 1f else 0.4f
    Box(
        modifier = Modifier
            .size(18.dp)
            .background(KtCream.copy(alpha = alpha), CircleShape)
    )
}
