package com.vp.a1076012510014_assignmentweek6.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vp.a1076012510014_assignmentweek6.model.Community
import com.vp.a1076012510014_assignmentweek6.model.Project
import com.vp.a1076012510014_assignmentweek6.ui.widgets.DashboardProjectCard
import com.vp.a1076012510014_assignmentweek6.ui.widgets.JoinedCommunityRow
import com.vp.a1076012510014_assignmentweek6.ui.widgets.SectionHeader
import com.vp.a1076012510014_assignmentweek6.ui.widgets.StatCard

@Composable
fun DashboardScreen(
    joinedCommunities: List<Community>,
    projects: List<Project>,
    communityNameOf: (String) -> String,
    projectCountOf: (String) -> Int,
    onProjectClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F8FB))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .statusBarsPadding()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    "CampusTech Hub",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A)
                )
                Text(
                    "Student Profile & Portfolio Dashboard",
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }

            // Profile Pic
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF4338CA)),
                contentAlignment = Alignment.Center
            ) {
                Text("AP", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
        }

        HorizontalDivider(color = Color(0xFFE3E8EF))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(22.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(Color(0xFF1E3A8A), Color(0xFF3B82F6))
                            )
                        )
                        .padding(20.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(62.dp)
                                .clip(CircleShape)
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "AP",
                                color = Color(0xFF1E3A8A),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(
                                "MAHASISWA AKTIF",
                                color = Color.White,
                                fontSize = 9.sp,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50))
                                    .background(Color.White.copy(alpha = 0.2f))
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                "Aditya Pratama",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                "Teknik Informatika • Angkatan 2022",
                                color = Color.White.copy(alpha = 0.85f),
                                fontSize = 11.sp
                            )
                            Text(
                                "NIM: 22001045",
                                color = Color.White.copy(alpha = 0.85f),
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            }

            item {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    StatCard(
                        number = joinedCommunities.size.toString(),
                        title = "Joined Clubs",
                        subtitle = "Komunitas yang Diikuti",
                        icon = Icons.Default.Group,
                        iconBg = Color(0xFFE0EAFF),
                        iconTint = Color(0xFF2563EB),
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        number = projects.size.toString(),
                        title = "Total Projects",
                        subtitle = "Seluruh Proyek Dibuat",
                        icon = Icons.Default.ContentCopy,
                        iconBg = Color(0xFFEDE7FB),
                        iconTint = Color(0xFF7C3AED),
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                SectionHeader(
                    icon = {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFF2563EB),
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    title = "Semua Komunitas yang Diikuti (" + joinedCommunities.size + ")",
                    rightText = "Status: Joined"
                )
            }

            if (joinedCommunities.isEmpty()) {
                item {
                    Text("Belum bergabung dengan komunitas", color = Color.Gray, fontSize = 13.sp)
                }
            } else {
                items(joinedCommunities, key = { it.id }) { c ->
                    JoinedCommunityRow(
                        community = c,
                        projectCount = projectCountOf(c.id)
                    )
                }
            }

            item {
                SectionHeader(
                    icon = {
                        Icon(
                            Icons.Default.ContentCopy,
                            contentDescription = null,
                            tint = Color(0xFF7C3AED),
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    title = "Semua Proyek Mahasiswa (" + projects.size + ")",
                    rightText = "Total: " + projects.size + " Proyek"
                )
            }

            if (projects.isEmpty()) {
                item {
                    Text("Belum ada proyek yang dicatat", color = Color.Gray, fontSize = 13.sp)
                }
            } else {
                items(projects, key = { it.id }) { p ->
                    DashboardProjectCard(
                        project = p,
                        communityName = communityNameOf(p.communityId),
                        onClick = { onProjectClick(p.id) }
                    )
                }
            }
        }
    }
}