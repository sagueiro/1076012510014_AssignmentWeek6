package com.vp.a1076012510014_assignmentweek6.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vp.a1076012510014_assignmentweek6.model.Community

@Composable
fun CommunityCard(
    community: Community,
    onToggleJoin: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE3E8EF))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconBox(
                    icon = getCommunityIcon(community.id),
                    bg = Color(community.color).copy(alpha = 0.15f),
                    tint = Color(community.color)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(community.name, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
                    Text(
                        community.fullName + " • " + community.members + " Mahasiswa",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(community.description, fontSize = 13.sp, color = Color(0xFF334155))
            HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (community.isJoined) {
                    Text(
                        "Unlocked • Buka Proyek →",
                        fontSize = 12.sp,
                        color = Color(0xFF1D4ED8),
                        fontWeight = FontWeight.Bold
                    )
                    OutlinedButton(
                        onClick = onToggleJoin,
                        border = BorderStroke(1.dp, Color(0xFFC62828)),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFC62828))
                    ) {
                        Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Joined")
                    }
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Lock,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Terkunci (Gate Akses)", fontSize = 12.sp, color = Color.Gray)
                    }
                    Button(onClick = onToggleJoin) {
                        Text("+ Join")
                    }
                }
            }
        }
    }
}