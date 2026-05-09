package com.kidstap.app.ui.result

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kidstap.app.ui.components.ButtonStyle
import com.kidstap.app.ui.components.KidsTapButton
import com.kidstap.app.ui.components.KidsTapCard
import com.kidstap.app.ui.navigation.Route
import com.kidstap.app.ui.theme.*

@Composable
fun ResultScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(KtYellow)
    ) {
        // Confetti could be placed here if a particle system is added
        
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            // Left Half
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(60.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text("ROUND COMPLETE", style = KidsTapTheme.typography.labelMedium, color = KtInk.copy(alpha = 0.7f))
                Spacer(modifier = Modifier.height(14.dp))
                Text("Great job!", style = KidsTapTheme.typography.displayLarge)
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    "You reached your target score. Try a higher target next round?",
                    style = KidsTapTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Stars
                Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                    Star(on = true)
                    Star(on = true)
                    Star(on = false)
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    KidsTapButton("↻ Play again", onClick = { navController.popBackStack() }, style = ButtonStyle.Red)
                    KidsTapButton("🏠 Home", onClick = { 
                        navController.navigate(Route.Home.route) { popUpTo(Route.Home.route) { inclusive = true } }
                    }, style = ButtonStyle.Cream)
                }
            }
            
            // Right Half
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(60.dp),
                verticalArrangement = Arrangement.Center
            ) {
                // Score Card
                KidsTapCard(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = KtCream
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)
                    ) {
                        Text("FINAL SCORE", style = KidsTapTheme.typography.labelMedium, color = KtInk.copy(alpha = 0.5f))
                        Text("17", fontSize = 140.sp, fontWeight = FontWeight.Bold, color = KtBlue, modifier = Modifier.padding(vertical = 6.dp))
                        Text("★ New high score • +3 from last time", style = KidsTapTheme.typography.bodySmall, fontWeight = FontWeight.Bold, color = KtInk.copy(alpha = 0.7f))
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Stats Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    StatCard(modifier = Modifier.weight(1f), value = "82s", label = "Time")
                    StatCard(modifier = Modifier.weight(1f), value = "5", label = "Best streak")
                    StatCard(modifier = Modifier.weight(1f), value = "94%", label = "Accuracy")
                }
            }
        }
    }
}

@Composable
private fun Star(on: Boolean) {
    val bgColor = if (on) KtRed else KtCream
    val textColor = if (on) KtCream else KtInk
    Box(
        modifier = Modifier
            .size(64.dp)
            .neoBrutalismShadow(KtInk, 32.dp, 4.dp)
            .background(bgColor, CircleShape)
            .border(3.dp, KtInk, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text("★", fontSize = 32.sp, color = textColor)
    }
}

@Composable
private fun StatCard(modifier: Modifier, value: String, label: String) {
    Box(
        modifier = modifier
            .background(KtCream, RoundedCornerShape(18.dp))
            .border(2.5.dp, KtInk, RoundedCornerShape(18.dp))
            .padding(vertical = 14.dp, horizontal = 16.dp)
    ) {
        Column {
            Text(value, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = KtInk)
            Text(label.uppercase(), style = KidsTapTheme.typography.labelSmall, color = KtInk.copy(alpha = 0.6f), modifier = Modifier.padding(top = 4.dp))
        }
    }
}
