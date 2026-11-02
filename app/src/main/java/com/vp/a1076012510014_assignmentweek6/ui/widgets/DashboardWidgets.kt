package com.vp.a1076012510014_assignmentweek6.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vp.a1076012510014_assignmentweek6.model.Community
import com.vp.a1076012510014_assignmentweek6.model.Project

fun getCommunityIcon(communityId: String): ImageVector {
    return when (communityId) {
        "gdsc" -> Icons.Default.Code
        "cyber" -> Icons.Default.Security
        "mobile" -> Icons.Default.PhoneAndroid
        "ai" -> Icons.Default.AutoAwesome
        "web" -> Icons.Default.Language
        "data" -> Icons.Default.BarChart
        else -> Icons.Default.Code
    }
}

fun getProjectIcon(project: Project): ImageVector {
    return when (project.id) {
        "proj-gdsc-01" -> Icons.Default.Android
        "proj-gdsc-02" -> Icons.Default.QrCodeScanner
        else -> getCommunityIcon(project.communityId)
    }
}

@Composable
fun IconBox(icon: ImageVector, bg: Color, tint: Color) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(bg),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(22.dp)
        )
    }
}

@Composable
fun SectionHeader(
    icon: @Composable () -> Unit,
    title: String,
    rightText: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon()
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            title,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color(0xFF0F172A),
            modifier = Modifier.weight(1f)
        )
        Text(
            rightText,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            color = Color(0xFF2563EB)
        )
    }
}

@Composable
fun JoinedCommunityRow(community: Community, projectCount: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE3E8EF))
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconBox(
                icon = getCommunityIcon(community.id),
                bg = Color(community.color).copy(alpha = 0.15f),
                tint = Color(community.color)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(community.name, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF0F172A))
                Text(
                    community.fullName + " • " + projectCount + " Proyek",
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }
            Text(
                "Joined ✓",
                color = Color(0xFFC62828),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFFEECEC))
                    .border(1.dp, Color(0xFFF5B5B5), RoundedCornerShape(8.dp))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
fun DashboardProjectCard(
    project: Project,
    communityName: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE3E8EF))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconBox(
                    icon = getProjectIcon(project),
                    bg = Color(0xFFE8EEF9),
                    tint = Color(0xFF1E3A8A)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(communityName, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2563EB))
                    Text(project.title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
                    Text(
                        project.techStack.take(3).joinToString(" • "),
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 10.dp),
                color = Color(0xFFEDF0F5)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                StatusBadge(project.isCompleted)
            }
        }
    }
}