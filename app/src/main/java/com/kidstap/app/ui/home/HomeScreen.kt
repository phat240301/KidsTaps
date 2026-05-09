package com.kidstap.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kidstap.app.ui.components.KidsTapCard
import com.kidstap.app.ui.components.KidsTapPill
import com.kidstap.app.ui.components.KidsTapSidebar
import com.kidstap.app.ui.navigation.Route
import com.kidstap.app.ui.theme.KidsTapTheme
import com.kidstap.app.ui.theme.KtBlue
import com.kidstap.app.ui.theme.KtCream
import com.kidstap.app.ui.theme.KtInk
import com.kidstap.app.ui.theme.KtRed
import com.kidstap.app.ui.theme.KtYellow
import com.kidstap.app.ui.theme.kidsTapCard

@Composable
fun HomeScreen(navController: NavController) {
    var lang by remember { mutableStateOf("EN") }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(KtCream)
    ) {
        // Left Sidebar
        KidsTapSidebar(
            activeRoute = "home",
            onNavigate = { route ->
                when (route) {
                    "settings" -> navController.navigate(Route.Settings.route)
                    "play" -> navController.navigate(Route.Setup.createRoute("shapes"))
                    "scores" -> navController.navigate(Route.Settings.route)
                }
            },
            lang = lang,
            onLangToggle = { lang = it }
        )

        // Right Content Area
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
                        text = "WELCOME BACK",
                        style = KidsTapTheme.typography.labelMedium,
                        color = KtBlue,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    Text(
                        text = "Pick a game to play",
                        style = KidsTapTheme.typography.displaySmall
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    KidsTapPill(text = "🔥 Streak • 3 days")
                    KidsTapPill(text = "▶ Quick play", isDark = true)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Game Modes Grid
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Left Column: Shapes Mode
                Box(
                    modifier = Modifier
                        .weight(1.4f)
                        .fillMaxHeight()
                        .kidsTapCard(backgroundColor = KtRed)
                        .clickable { navController.navigate(Route.Setup.createRoute("shapes")) }
                        .padding(28.dp)
                ) {
                    Column {
                        Text("MODE 01", style = KidsTapTheme.typography.labelSmall, color = KtCream.copy(alpha = 0.8f))
                        Text("Shapes", style = KidsTapTheme.typography.displayMedium, color = KtCream, modifier = Modifier.padding(top = 4.dp, bottom = 8.dp))
                        Text(
                            "Tap circles, squares, stars and hearts as they appear. Great for early shape recognition.",
                            style = KidsTapTheme.typography.bodySmall,
                            color = KtCream.copy(alpha = 0.85f),
                            modifier = Modifier.fillMaxWidth(0.6f)
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                            StatItem(value = "24", label = "High score")
                            StatItem(value = "12", label = "Sessions")
                            StatItem(value = "5", label = "Shapes")
                        }
                    }

                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.BottomEnd)
                            .kidsTapCard(backgroundColor = KtCream)
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("🔶", fontSize = 100.sp)
                    }
                }

                // Right Column: Animals Mode + Recent Scores
                Column(
                    modifier = Modifier.weight(1f).fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    // Animals Mode
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .kidsTapCard(backgroundColor = KtBlue)
                            .clickable { navController.navigate(Route.Setup.createRoute("animals")) }
                            .padding(24.dp)
                    ) {
                        Column {
                            Text("MODE 02", style = KidsTapTheme.typography.labelSmall, color = KtCream.copy(alpha = 0.8f))
                            Text("Animals", style = KidsTapTheme.typography.displaySmall, color = KtCream, modifier = Modifier.padding(top = 4.dp, bottom = 8.dp))
                            Text(
                                "Tap the animals as they appear. Each tap names the animal aloud.",
                                style = KidsTapTheme.typography.bodySmall,
                                color = KtCream.copy(alpha = 0.85f)
                            )
                        }
                        
                        Box(
                            modifier = Modifier
                                .size(130.dp)
                                .align(Alignment.BottomEnd)
                                .kidsTapCard(backgroundColor = KtCream),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("🐰", fontSize = 60.sp)
                        }
                    }

                    // Recent Scores Card
                    KidsTapCard(
                        backgroundColor = KtYellow,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            Text("RECENT", style = KidsTapTheme.typography.labelSmall, color = KtInk.copy(alpha = 0.6f), modifier = Modifier.padding(bottom = 10.dp))
                            Text("Your Scores", style = KidsTapTheme.typography.headlineMedium, color = KtInk, modifier = Modifier.padding(bottom = 4.dp))
                            
                            RecentRow("🔶", "Shapes • age 7", "17 pts", true)
                            RecentRow("🐰", "Animals • age 7", "14 pts", true)
                            RecentRow("🔶", "Shapes • age 6", "11 pts", false)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StatItem(value: String, label: String) {
    Column {
        Text(value, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = KtCream)
        Text(label, style = KidsTapTheme.typography.labelSmall, color = KtCream.copy(alpha = 0.8f))
    }
}

@Composable
private fun RecentRow(icon: String, name: String, score: String, showBorder: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .then(if (showBorder) Modifier.border(bottom = 1.dp, color = KtInk.copy(alpha = 0.2f)) else Modifier),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(icon, fontSize = 22.sp, modifier = Modifier.padding(end = 10.dp))
            Text(name, style = KidsTapTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = KtInk)
        }
        Text(score, style = KidsTapTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = KtInk)
    }
}

// Extension to allow single side border
fun Modifier.border(bottom: androidx.compose.ui.unit.Dp, color: androidx.compose.ui.graphics.Color) = this.drawBehind {
    val strokeWidth = bottom.toPx()
    val y = this.size.height - strokeWidth / 2
    drawLine(
        color = color,
        start = androidx.compose.ui.geometry.Offset(0f, y),
        end = androidx.compose.ui.geometry.Offset(this.size.width, y),
        strokeWidth = strokeWidth
    )
}
