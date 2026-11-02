package com.vp.a1076012510014_assignmentweek6.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vp.a1076012510014_assignmentweek6.model.Project
import com.vp.a1076012510014_assignmentweek6.ui.widgets.StatusBadge
import com.vp.a1076012510014_assignmentweek6.ui.widgets.TechBadge

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ProjectDetailScreen(
    project: Project?,
    communityName: String,
    onBack: () -> Unit,
    onToggleStatus: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Project Detail", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->

        if (project == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("Proyek tidak ditemukan")
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFF0F172A))
                    .padding(20.dp)
            ) {
                Text(
                    communityName,
                    color = Color.White,
                    fontSize = 11.sp,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xFF1D4ED8))
                        .padding(horizontal = 10.dp, vertical = 3.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(project.title, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Mark as Done", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))
                        StatusBadge(project.isCompleted)
                    }
                    Switch(
                        checked = project.isCompleted,
                        onCheckedChange = { onToggleStatus() }
                    )
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text("Deskripsi Lengkap Proyek", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(project.description, fontSize = 13.sp)
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text("Tech Stack Terpilih", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        for (tech in project.techStack) {
                            TechBadge(tech)
                        }
                    }
                }
            }
        }
    }
}