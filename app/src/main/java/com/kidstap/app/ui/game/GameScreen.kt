package com.kidstap.app.ui.game

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kidstap.app.domain.model.GameState
import com.kidstap.app.domain.model.ShapeType
import com.kidstap.app.domain.model.SpawnableItem
import com.kidstap.app.ui.components.ButtonStyle
import com.kidstap.app.ui.components.KidsTapButton
import com.kidstap.app.ui.components.KidsTapCard
import com.kidstap.app.ui.navigation.Route
import com.kidstap.app.ui.theme.*
import kotlin.math.roundToInt

@Composable
fun GameScreen(
    navController: NavController,
    viewModel: GameViewModel = hiltViewModel(),
) {
    val gameState by viewModel.gameState.collectAsState()
    var isPaused by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        onDispose { viewModel.endGame() }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BrushGradient())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // HUD
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Quit
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .neoBrutalismShadow(KtInk, 16.dp, 3.dp)
                    .background(KtCream, RoundedCornerShape(16.dp))
                    .border(2.5.dp, KtInk, RoundedCornerShape(16.dp))
                    .clickable { navController.popBackStack() },
                contentAlignment = Alignment.Center
            ) { Text("✕", fontSize = 22.sp, fontWeight = FontWeight.Bold) }

            if (gameState is GameState.Active) {
                val state = gameState as GameState.Active
                HudPill("★ ${state.currentScore} / ${state.targetScore}")
                
                // Progress
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(18.dp)
                        .background(KtSoft, RoundedCornerShape(9.dp))
                        .border(2.5.dp, KtInk, RoundedCornerShape(9.dp))
                ) {
                    val fraction = if(state.targetScore > 0) state.currentScore.toFloat() / state.targetScore else 0f
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(fraction)
                            .fillMaxHeight()
                            .background(KtGreen, RoundedCornerShape(9.dp))
                            .border(2.5.dp, KtInk, RoundedCornerShape(9.dp))
                    )
                    Text("ROUND PROGRESS", style = KidsTapTheme.typography.labelSmall, modifier = Modifier.align(Alignment.CenterStart).padding(start = 14.dp))
                }

                HudPill("⏱ ${state.elapsedSeconds}s")
                HudPill("🔥 0") // Streak placeholder
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }

            // Pause
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .neoBrutalismShadow(KtInk, 16.dp, 3.dp)
                    .background(KtCream, RoundedCornerShape(16.dp))
                    .border(2.5.dp, KtInk, RoundedCornerShape(16.dp))
                    .clickable { isPaused = true },
                contentAlignment = Alignment.Center
            ) { Text("⏸", fontSize = 22.sp, fontWeight = FontWeight.Bold) }
        }

        // Game Area
        BoxWithConstraints(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .neoBrutalismShadow(KtInk, 28.dp, 6.dp)
                .background(KtCream, RoundedCornerShape(28.dp))
                .border(3.dp, KtInk, RoundedCornerShape(28.dp))
                .clip(RoundedCornerShape(28.dp))
                .drawGrid()
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        if (!isPaused) viewModel.onGameAreaTap(offset.x, offset.y)
                    }
                }
        ) {
            val width = constraints.maxWidth.toFloat()
            val height = constraints.maxHeight.toFloat()
            val state = gameState

            LaunchedEffect(width, height) {
                if (state is GameState.Idle) {
                    val config = viewModel.gameConfig.value ?: com.kidstap.app.domain.model.GameConfig(
                        mode = com.kidstap.app.domain.model.GameMode.SHAPES,
                        childAge = 5
                    )
                    viewModel.startGame(config, width, height)
                }
            }

            if (state is GameState.Active && !isPaused) {
                state.visibleItems.forEach { item ->
                    GameItemView(item)
                }
            }

            if (isPaused) {
                PauseOverlay(
                    onResume = { isPaused = false },
                    onHome = {
                        navController.navigate(Route.Home.route) {
                            popUpTo(Route.Home.route) { inclusive = true }
                        }
                    }
                )
            }

            if (state is GameState.Finished) {
                LaunchedEffect(Unit) {
                    kotlinx.coroutines.delay(1000)
                    navController.navigate(Route.Result.route) {
                        popUpTo(Route.Game.route) { inclusive = true }
                    }
                }
            }
        }
    }
}

@Composable
private fun HudPill(text: String) {
    Box(
        modifier = Modifier
            .background(KtInk, RoundedCornerShape(16.dp))
            .padding(horizontal = 18.dp, vertical = 10.dp)
    ) {
        Text(text, color = KtCream, style = KidsTapTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun GameItemView(item: SpawnableItem) {
    val scale by animateFloatAsState(
        targetValue = 1f, 
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow),
        label = "spawn"
    )

    val xOffset = item.centerX - (item.sizePixels / 2f)
    val yOffset = item.centerY - (item.sizePixels / 2f)

    Box(
        modifier = Modifier
            .offset { IntOffset(xOffset.roundToInt(), yOffset.roundToInt()) }
            .size(item.sizePixels.dp) // Converting px to dp roughly for visual, in reality might need density
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
    ) {
        when (item) {
            is SpawnableItem.Shape -> ShapeItemView(item)
            is SpawnableItem.Animal -> AnimalItemView(item)
        }
    }
}

@Composable
private fun ShapeItemView(shape: SpawnableItem.Shape) {
    val shapeRadius = if (shape.type == ShapeType.CIRCLE) CircleShape else RoundedCornerShape(32.dp)
    val icon = when (shape.type) {
        ShapeType.CIRCLE -> "●"
        ShapeType.SQUARE -> "■"
        ShapeType.TRIANGLE -> "▲"
        ShapeType.STAR -> "★"
        ShapeType.HEART -> "♥"
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .neoBrutalismShadow(KtInk, if (shape.type == ShapeType.CIRCLE) 999.dp else 32.dp, 6.dp)
            .background(shape.color, shapeRadius)
            .border(3.5.dp, KtInk, shapeRadius),
        contentAlignment = Alignment.Center
    ) {
        Text(icon, fontSize = 60.sp, color = KtCream)
    }
}

@Composable
private fun AnimalItemView(animal: SpawnableItem.Animal) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .neoBrutalismShadow(KtInk, 32.dp, 6.dp)
            .background(KtCream, RoundedCornerShape(32.dp))
            .border(3.5.dp, KtInk, RoundedCornerShape(32.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(animal.type.emoji, fontSize = 60.sp)
    }
}

@Composable
private fun PauseOverlay(onResume: () -> Unit, onHome: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x8C1A1A2E)), // rgba(26,26,46,0.55)
        contentAlignment = Alignment.Center
    ) {
        KidsTapCard(modifier = Modifier.width(480.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Text("Paused", style = KidsTapTheme.typography.displaySmall)
                Text("Take a breath. Tap resume when ready.", style = KidsTapTheme.typography.bodySmall, color = KtInk.copy(alpha = 0.65f))
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    KidsTapButton("🏠 Home", onClick = onHome, modifier = Modifier.weight(1f), style = ButtonStyle.Cream)
                    KidsTapButton("▶ Resume", onClick = onResume, modifier = Modifier.weight(1f), style = ButtonStyle.Blue)
                }
            }
        }
    }
}

fun Modifier.drawGrid() = this.drawBehind {
    val gridSize = 60.dp.toPx()
    val inkAlpha = KtInk.copy(alpha = 0.04f)
    
    // Draw vertical lines
    var x = 0f
    while (x < size.width) {
        drawLine(inkAlpha, start = Offset(x, 0f), end = Offset(x, size.height), strokeWidth = 1f)
        x += gridSize
    }
    // Draw horizontal lines
    var y = 0f
    while (y < size.height) {
        drawLine(inkAlpha, start = Offset(0f, y), end = Offset(size.width, y), strokeWidth = 1f)
        y += gridSize
    }
}

private fun BrushGradient() = androidx.compose.ui.graphics.Brush.verticalGradient(
    colors = listOf(Color(0xFFFFF8EC), Color(0xFFFFEED1))
)
