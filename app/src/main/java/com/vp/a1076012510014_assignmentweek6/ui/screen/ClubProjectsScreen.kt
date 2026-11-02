package com.vp.a1076012510014_assignmentweek6.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vp.a1076012510014_assignmentweek6.data.CampusRepository
import com.vp.a1076012510014_assignmentweek6.model.Community
import com.vp.a1076012510014_assignmentweek6.model.Project
import com.vp.a1076012510014_assignmentweek6.ui.widgets.AddProjectDialog
import com.vp.a1076012510014_assignmentweek6.ui.widgets.ProjectCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClubProjectsScreen(
    community: Community?,
    projects: List<Project>,
    onBack: () -> Unit,
    onProjectClick: (String) -> Unit,
    onDelete: (String) -> Unit,
    onAddProject: (String, String, List<String>) -> Unit
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }

    if (community == null) {
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(community.name + " Projects", fontWeight = FontWeight.Bold)
                        Text(community.fullName, fontSize = 12.sp, color = Color.Gray)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Add Project")
            }
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(community.fullName, fontWeight = FontWeight.Bold)
                        Text(
                            community.members.toString() + " Anggota • " + projects.size + " Proyek Aktif",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }

            item {
                Text("Daftar Proyek Mahasiswa (" + projects.size + ")", fontWeight = FontWeight.Bold)
            }

            if (projects.isEmpty()) {
                item {
                    Text("Belum ada proyek yang dicatat", color = Color.Gray)
                }
            } else {
                items(projects, key = { it.id }) { p ->
                    ProjectCard(
                        project = p,
                        communityName = community.name,
                        onClick = { onProjectClick(p.id) },
                        onDelete = { onDelete(p.id) }
                    )
                }
            }
        }
    }

    if (showDialog) {
        AddProjectDialog(
            communityName = community.fullName,
            techOptions = CampusRepository.techOptions,
            onCancel = { showDialog = false },
            onSave = { title, desc, stack ->
                onAddProject(title, desc, stack)
                showDialog = false
            }
        )
    }
}