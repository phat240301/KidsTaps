package com.kidstap.app.ui.setup

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kidstap.app.domain.DifficultyCalculator
import com.kidstap.app.domain.model.GameMode
import com.kidstap.app.ui.components.*
import com.kidstap.app.ui.navigation.Route
import com.kidstap.app.ui.theme.*

@Composable
fun GameSetupScreen(navController: NavController, mode: String) {
    val gameMode = if (mode == "animals") GameMode.ANIMALS else GameMode.SHAPES
    
    var age by remember { mutableStateOf(7) }
    var spawnInterval by remember { mutableStateOf(3.4f) }
    var holdDuration by remember { mutableStateOf(3.9f) }
    var targetScore by remember { mutableStateOf(17) }
    var musicEnabled by remember { mutableStateOf(true) }
    var calmMode by remember { mutableStateOf(false) }
    var voicePrompts by remember { mutableStateOf(true) }
    var hapticsEnabled by remember { mutableStateOf(true) }

    fun autoSuggest() {
        val config = DifficultyCalculator.calculateForAge(age, gameMode)
        spawnInterval = config.spawnIntervalSeconds
        holdDuration = config.holdDurationSeconds
        targetScore = config.targetScore
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(KtCream)
    ) {
        // Sidebar
        KidsTapSidebar(
            activeRoute = "play",
            onNavigate = { route ->
                if (route == "home") navController.navigate(Route.Home.route)
            },
            lang = "EN",
            onLangToggle = {}
        )

        // Content
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(horizontal = 40.dp, vertical = 32.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text(
                        text = "STEP 1 OF 2 • TEACHER",
                        style = KidsTapTheme.typography.labelMedium,
                        color = KtBlue,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    Text(
                        text = "Set up the round",
                        style = KidsTapTheme.typography.displaySmall
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    KidsTapPill(
                        text = "← Back", 
                        onClick = { navController.popBackStack() }
                    )
                    KidsTapButton(
                        text = "✓ Start game",
                        style = ButtonStyle.Blue,
                        onClick = { navController.navigate(Route.Countdown.route) },
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Grid
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Left Card: Difficulty
                KidsTapCard(
                    modifier = Modifier.weight(1.1f).fillMaxHeight()
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        Column {
                            Text("DIFFICULTY", style = KidsTapTheme.typography.labelMedium, color = KtInk.copy(alpha = 0.6f))
                            Text("Tune for the child", style = KidsTapTheme.typography.headlineMedium)
                        }

                        // Age Block
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(KtYellow, RoundedCornerShape(22.dp))
                                .padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(age.toString(), fontSize = 88.sp, fontWeight = FontWeight.Bold, lineHeight = 88.sp)
                            Spacer(modifier = Modifier.width(18.dp))
                            Column {
                                Text("CHILD'S AGE", style = KidsTapTheme.typography.labelSmall, color = KtInk.copy(alpha = 0.7f))
                                Text("years old", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            // Stepper
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                StepBtn("-") { if (age > 3) age-- }
                                StepBtn("+") { if (age < 12) age++ }
                            }
                        }

                        // Auto-suggest
                        Box(
                            modifier = Modifier
                                .height(52.dp)
                                .fillMaxWidth()
                                .background(KtInk, RoundedCornerShape(16.dp))
                                .clip(RoundedCornerShape(16.dp))
                                .clickable { autoSuggest() },
                            contentAlignment = Alignment.Center
                        ) {
                            Text("✦ AUTO-SUGGEST DIFFICULTY", color = KtCream, style = KidsTapTheme.typography.labelMedium)
                        }

                        // Sliders
                        CustomSliderRow("Spawn speed", "%.1f s".format(spawnInterval), "How often a new item appears", spawnInterval, 1.5f..8.0f) { spawnInterval = it }
                        CustomSliderRow("Hold time", "%.1f s".format(holdDuration), "How long an item stays visible", holdDuration, 2.0f..10.0f) { holdDuration = it }
                        CustomSliderRow("Target score", "$targetScore pts", "Round ends when reached", targetScore.toFloat(), 5f..30f) { targetScore = it.toInt() }
                    }
                }

                // Right Card: Options
                KidsTapCard(
                    modifier = Modifier.weight(1f).fillMaxHeight()
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        Column {
                            Text("MODE • ${gameMode.name}", style = KidsTapTheme.typography.labelMedium, color = KtInk.copy(alpha = 0.6f))
                            Text("Preview & comfort", style = KidsTapTheme.typography.headlineMedium)
                        }

                        // Preview Box
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .background(KtSoft, RoundedCornerShape(22.dp))
                                .border(3.dp, KtInk, RoundedCornerShape(22.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("LIVE PREVIEW", style = KidsTapTheme.typography.labelSmall, color = KtInk.copy(alpha = 0.55f), modifier = Modifier.align(Alignment.TopStart).padding(18.dp))
                            // Dummy shape
                            Box(modifier = Modifier.size(100.dp).neoBrutalismShadow(KtInk, 50.dp, 5.dp).background(KtRed, CircleShape).border(3.dp, KtInk, CircleShape))
                        }

                        // Toggles Grid
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            OptCard(modifier = Modifier.weight(1f), "Music", "Calm loop", musicEnabled) { musicEnabled = it }
                            OptCard(modifier = Modifier.weight(1f), "Calm Mode", "Reduce motion", calmMode) { calmMode = it }
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            OptCard(modifier = Modifier.weight(1f), "Voice prompts", "Name each item", voicePrompts) { voicePrompts = it }
                            OptCard(modifier = Modifier.weight(1f), "Haptics", "Vibrate on tap", hapticsEnabled) { hapticsEnabled = it }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StepBtn(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(52.dp)
            .neoBrutalismShadow(KtInk, 14.dp, 3.dp)
            .background(KtCream, RoundedCornerShape(14.dp))
            .border(2.dp, KtInk, RoundedCornerShape(14.dp))
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text, fontSize = 26.sp, fontWeight = FontWeight.Bold, modifier = Modifier.offset(y = (-2).dp))
    }
}

@Composable
private fun CustomSliderRow(name: String, valStr: String, hint: String, value: Float, range: ClosedFloatingPointRange<Float>, onValueChange: (Float) -> Unit) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
            Text(name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Text(valStr, style = KidsTapTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = KtBlue)
        }
        KidsTapSlider(value = value, onValueChange = onValueChange, valueRange = range)
        Text(hint, style = KidsTapTheme.typography.bodySmall, color = KtInk.copy(alpha = 0.55f))
    }
}

@Composable
private fun OptCard(modifier: Modifier = Modifier, title: String, desc: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = modifier
            .border(2.dp, KtInk, RoundedCornerShape(16.dp))
            .padding(14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(title, fontSize = 15.sp, fontWeight = FontWeight.Bold)
            Text(desc, style = KidsTapTheme.typography.bodySmall, color = KtInk.copy(alpha = 0.6f))
        }
        KidsTapSwitch(checked = checked, onCheckedChange = onCheckedChange)
    }
}
