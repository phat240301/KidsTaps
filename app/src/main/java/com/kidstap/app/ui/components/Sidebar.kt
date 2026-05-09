package com.kidstap.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kidstap.app.ui.theme.KidsTapTheme
import com.kidstap.app.ui.theme.KtBlue
import com.kidstap.app.ui.theme.KtCream
import com.kidstap.app.ui.theme.KtInk
import com.kidstap.app.ui.theme.KtYellow

@Composable
fun KidsTapSidebar(
    activeRoute: String,
    onNavigate: (String) -> Unit,
    lang: String = "EN",
    onLangToggle: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .width(280.dp)
            .fillMaxHeight()
            .background(KtInk)
            .padding(vertical = 28.dp, horizontal = 22.dp)
    ) {
        // Brand / Logo
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 28.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(KtYellow, RoundedCornerShape(16.dp))
                    .border(3.dp, KtCream, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("KT", color = KtInk, fontWeight = FontWeight.Bold, fontSize = 26.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "Kid Taps",
                    color = KtCream,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    lineHeight = 24.sp
                )
                Text(
                    text = "V1.0 • OFFLINE",
                    color = KtCream.copy(alpha = 0.5f),
                    style = KidsTapTheme.typography.labelSmall
                )
            }
        }

        // Navigation Items
        SidebarItem(icon = "🏠", label = "Home", isActive = activeRoute == "home") { onNavigate("home") }
        SidebarItem(icon = "▶", label = "Play", isActive = activeRoute == "play") { onNavigate("play") }
        SidebarItem(icon = "⭐", label = "High Scores", isActive = activeRoute == "scores") { onNavigate("scores") }
        SidebarItem(icon = "⚙", label = "Settings", isActive = activeRoute == "settings") { onNavigate("settings") }

        Spacer(modifier = Modifier.weight(1f))

        // Footer Actions
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(KtCream.copy(alpha = 0.08f), CircleShape)
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LangOption("EN", isActive = lang == "EN") { onLangToggle("EN") }
            LangOption("VI", isActive = lang == "VI") { onLangToggle("VI") }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Teacher Mode Pill
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(KtBlue, RoundedCornerShape(14.dp))
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("👩‍🏫 Teacher mode", color = KtCream, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
    }
}

@Composable
private fun SidebarItem(icon: String, label: String, isActive: Boolean, onClick: () -> Unit) {
    val bgColor = if (isActive) KtCream.copy(alpha = 0.1f) else Color.Transparent
    val alpha = if (isActive) 1f else 0.65f
    val borderModifier = if (isActive) Modifier.border(width = 4.dp, color = KtYellow, shape = RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp)) else Modifier

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(bgColor, RoundedCornerShape(14.dp))
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClick() }
            .then(if(isActive) Modifier.padding(start = 0.dp) else Modifier.padding(start = 4.dp)) // compensate for border visually if needed
            .padding(horizontal = 14.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isActive) {
            Box(modifier = Modifier.width(4.dp).height(24.dp).background(KtYellow, RoundedCornerShape(2.dp)))
            Spacer(modifier = Modifier.width(10.dp))
        } else {
            Spacer(modifier = Modifier.width(14.dp))
        }

        Text(text = icon, fontSize = 18.sp, modifier = Modifier.width(28.dp))
        Text(
            text = label,
            color = KtCream.copy(alpha = alpha),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun RowScope.LangOption(label: String, isActive: Boolean, onClick: () -> Unit) {
    val bgColor = if (isActive) KtYellow else Color.Transparent
    val textColor = if (isActive) KtInk else KtCream.copy(alpha = 0.6f)
    
    Box(
        modifier = Modifier
            .weight(1f)
            .background(bgColor, CircleShape)
            .clip(CircleShape)
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = textColor,
            style = KidsTapTheme.typography.labelSmall
        )
    }
}
