package com.vp.a1076012510014_assignmentweek6.navigation

import kotlinx.serialization.Serializable
@Serializable
object DashboardRoute
@Serializable
object CommunitiesRoute
@Serializable
data class ClubProjectsRoute(val communityId: String)
@Serializable
data class ProjectDetailRoute(val projectId: String)