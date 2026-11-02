package com.vp.a1076012510014_assignmentweek6.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.vp.a1076012510014_assignmentweek6.data.CampusRepository
import com.vp.a1076012510014_assignmentweek6.model.Community
import com.vp.a1076012510014_assignmentweek6.model.Project

class CampusViewModel : ViewModel() {

    val communities = mutableStateListOf<Community>()
    val projects = mutableStateListOf<Project>()
    private var nextProjectNumber = 100

    init {
        communities.addAll(CampusRepository.getCommunities())
        projects.addAll(CampusRepository.getProjects())
    }

    fun toggleJoin(communityId: String) {
        for (i in communities.indices) {
            if (communities[i].id == communityId) {
                communities[i] = communities[i].copy(isJoined = !communities[i].isJoined)
            }
        }
    }
    fun getJoinedCommunities(): List<Community> {
        return communities.filter { it.isJoined }
    }
    fun getCommunityById(id: String): Community? {
        return communities.find { it.id == id }
    }
    fun getCommunityName(id: String): String {
        val c = getCommunityById(id)
        return c?.name ?: "-"
    }
    fun getProjectsByCommunity(communityId: String): List<Project> {
        return projects.filter { it.communityId == communityId }
    }
    fun countProjects(communityId: String): Int {
        return getProjectsByCommunity(communityId).size
    }
    fun getProjectById(id: String): Project? {
        return projects.find { it.id == id }
    }
    fun addProject(communityId: String, title: String, description: String, techStack: List<String>) {
        val newId = "proj-" + communityId + "-" + nextProjectNumber
        nextProjectNumber++
        projects.add(Project(newId, communityId, title, description, techStack, false))
    }
    fun deleteProject(projectId: String) {
        projects.removeAll { it.id == projectId }
    }
    fun toggleProjectStatus(projectId: String) {
        for (i in projects.indices) {
            if (projects[i].id == projectId) {
                projects[i] = projects[i].copy(isCompleted = !projects[i].isCompleted)
            }
        }
    }
}