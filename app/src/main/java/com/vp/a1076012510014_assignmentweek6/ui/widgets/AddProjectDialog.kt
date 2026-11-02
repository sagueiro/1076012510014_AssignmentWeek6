package com.vp.a1076012510014_assignmentweek6.ui.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AddProjectDialog(
    communityName: String,
    techOptions: List<String>,
    onCancel: () -> Unit,
    onSave: (String, String, List<String>) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    val selectedTech = remember { mutableStateListOf<String>() }
    val canSave = title.isNotBlank() && desc.isNotBlank() && selectedTech.isNotEmpty()

    AlertDialog(
        onDismissRequest = { },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
        title = {
            Column {
                Text("Add Project", fontWeight = FontWeight.Bold)
                Text("Komunitas: $communityName", fontSize = 12.sp, color = Color.Gray)
            }
        },
        text = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Judul Proyek *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = desc,
                    onValueChange = { desc = it },
                    label = { Text("Deskripsi Proyek *") },
                    minLines = 3,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    "Tech Stack * (" + selectedTech.size + " Dipilih)",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    for (tech in techOptions) {
                        FilterChip(
                            selected = selectedTech.contains(tech),
                            onClick = {
                                if (selectedTech.contains(tech)) {
                                    selectedTech.remove(tech)
                                } else {
                                    selectedTech.add(tech)
                                }
                            },
                            label = { Text(tech) }
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { onSave(title, desc, selectedTech.toList()) },
                enabled = canSave
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text("Cancel")
            }
        }
    )
}