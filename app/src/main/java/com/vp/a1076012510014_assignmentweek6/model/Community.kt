package com.vp.a1076012510014_assignmentweek6.model

data class Community(
    val id: String,
    val name: String,
    val fullName: String,
    val description: String,
    val members: Int,
    val color: Long,
    val isJoined: Boolean
)