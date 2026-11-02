package com.vp.a1076012510014_assignmentweek6.model

data class Project(
    val id: String,
    val communityId: String,
    val title: String,
    val description: String,
    val techStack: List<String>,
    val isCompleted: Boolean
)