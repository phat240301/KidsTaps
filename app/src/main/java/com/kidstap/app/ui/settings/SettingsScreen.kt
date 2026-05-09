package com.kidstap.app.ui.settings

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kidstap.app.ui.components.*
import com.kidstap.app.ui.navigation.Route
import com.kidstap.app.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(navController: NavController) {
    var isUnlocked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(KtCream)
    ) {
        // Sidebar
        KidsTapSidebar(
            activeRoute = "settings",
            onNavigate = { route ->
                if (route == "home") navController.navigate(Route.Home.route)
                // Handle others
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
                        text = if (isUnlocked) "PARENT • UNLOCKED" else "PARENT • LOCKED",
                        style = KidsTapTheme.typography.labelMedium,
                        color = KtBlue,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    Text(
                        text = "Settings",
                        style = KidsTapTheme.typography.displaySmall
                    )
                }
                KidsTapPill(
                    text = "← Back", 
                    onClick = { navController.popBackStack() }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (!isUnlocked) {
                ParentGate { isUnlocked = true }
            } else {
                SettingsGrid()
            }
        }
    }
}

@Composable
private fun ParentGate(onUnlock: () -> Unit) {
    var isHolding by remember { mutableStateOf(false) }
    var holdProgress by remember { mutableStateOf(0f) }

    val animatedProgress by animateFloatAsState(
        targetValue = holdProgress,
        animationSpec = tween(if (isHolding) 100 else 300),
        label = "unlock"
    )

    LaunchedEffect(isHolding) {
        if (isHolding) {
            while (holdProgress < 1f) {
                delay(100)
                holdProgress += 0.033f // Approx 3 seconds
            }
            if (holdProgress >= 1f) {
                onUnlock()
            }
        } else {
            holdProgress = 0f
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(KtInk, RoundedCornerShape(28.dp))
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .background(KtYellow, RoundedCornerShape(22.dp))
                .border(3.dp, KtCream, RoundedCornerShape(22.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("🔒", fontSize = 30.sp)
        }
        
        Spacer(modifier = Modifier.width(18.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text("Hold to unlock parent settings", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = KtCream)
            Text("Press & hold for 3 seconds — keeps little hands away", style = KidsTapTheme.typography.bodySmall, color = KtCream.copy(alpha = 0.7f))
        }

        // Hold Button with Circular Progress
        Box(
            modifier = Modifier
                .size(64.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            isHolding = true
                            tryAwaitRelease()
                            isHolding = false
                        }
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.material3.CircularProgressIndicator(
                progress = { animatedProgress },
                modifier = Modifier.fillMaxSize(),
                color = KtGreen,
                trackColor = Color.White.copy(alpha = 0.15f),
                strokeWidth = 6.dp
            )
            Box(modifier = Modifier.size(46.dp).background(KtInk, CircleShape))
        }
    }
}

@Composable
private fun SettingsGrid() {
    var musicVol by remember { mutableStateOf(0.6f) }
    var sfxVol by remember { mutableStateOf(0.8f) }
    var calmMode by remember { mutableStateOf(false) }
    var bgm by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Col 1
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(24.dp)) {
            // Audio
            KidsTapCard(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Audio", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        Text("2 controls", style = KidsTapTheme.typography.labelSmall, color = KtInk.copy(alpha = 0.5f))
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    VolSlider("Music volume", "Calm background loop during play", musicVol) { musicVol = it }
                    VolSlider("Sound effects", "Tap sounds, fanfares, countdown", sfxVol) { sfxVol = it }
                }
            }
            
            // Language
            KidsTapCard(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Text("Language", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Column {
                            Text("App language", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                            Text("Switch between English & Vietnamese", style = KidsTapTheme.typography.bodySmall, color = KtInk.copy(alpha = 0.6f))
                        }
                        // Custom toggle
                        Row(modifier = Modifier.background(KtSoft, CircleShape).border(2.dp, KtInk, CircleShape).padding(4.dp)) {
                            Box(modifier = Modifier.background(KtInk, CircleShape).padding(horizontal = 14.dp, vertical = 6.dp)) { Text("EN", color = KtCream, fontWeight = FontWeight.Bold, fontSize = 12.sp) }
                            Box(modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)) { Text("VI", color = KtInk.copy(alpha = 0.6f), fontWeight = FontWeight.Bold, fontSize = 12.sp) }
                        }
                    }
                }
            }
        }

        // Col 2
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(24.dp)) {
            // Comfort
            KidsTapCard(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Text("Comfort", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                    SettingToggleRow("Calm Mode", "Reduces animations and visual effects", calmMode) { calmMode = it }
                    SettingToggleRow("Background music", "Play during games", bgm) { bgm = it }
                }
            }

            // Data
            KidsTapCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text("Data", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Column {
                            Text("High scores", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                            Text("Shapes: 24 • Animals: 18", style = KidsTapTheme.typography.bodySmall, color = KtInk.copy(alpha = 0.6f))
                        }
                        KidsTapPill("View")
                    }
                    DangerButton("⟲ Reset high scores")
                    DangerButton("🗑 Clear all settings")
                }
            }
        }
    }
}

@Composable
private fun VolSlider(name: String, desc: String, value: Float, onValueChange: (Float) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                Text(desc, style = KidsTapTheme.typography.bodySmall, color = KtInk.copy(alpha = 0.6f))
            }
            Text("${(value * 100).toInt()}%", style = KidsTapTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = KtBlue)
        }
        KidsTapSlider(value = value, onValueChange = onValueChange)
    }
}

@Composable
private fun SettingToggleRow(name: String, desc: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Text(desc, style = KidsTapTheme.typography.bodySmall, color = KtInk.copy(alpha = 0.6f))
        }
        KidsTapSwitch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

@Composable
private fun DangerButton(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(KtCream, RoundedCornerShape(16.dp))
            .drawBehind { // Dashed border
                val stroke = androidx.compose.ui.graphics.drawscope.Stroke(
                    width = 2.5.dp.toPx(),
                    pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(floatArrayOf(15f, 15f))
                )
                drawRoundRect(KtRed, style = stroke, cornerRadius = androidx.compose.ui.geometry.CornerRadius(16.dp.toPx()))
            }
            .clip(RoundedCornerShape(16.dp))
            .clickable { },
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = KtRed, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}
