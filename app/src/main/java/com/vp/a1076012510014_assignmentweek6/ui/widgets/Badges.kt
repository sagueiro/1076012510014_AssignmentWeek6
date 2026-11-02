package com.vp.a1076012510014_assignmentweek6.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun StatusBadge(isCompleted: Boolean) {
    var bg = Color(0xFFFEF3C7)
    var fg = Color(0xFF92400E)
    var text = "● In Progress"

    if (isCompleted) {
        bg = Color(0xFFDCFCE7)
        fg = Color(0xFF166534)
        text = "● Completed"
    }

    Text(
        text = text,
        color = fg,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(bg)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    )
}

@Composable
fun TechBadge(text: String) {
    Text(
        text = text,
        fontSize = 11.sp,
        color = Color(0xFF334155),
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFE8ECF4))
            .padding(horizontal = 8.dp, vertical = 3.dp)
    )
}