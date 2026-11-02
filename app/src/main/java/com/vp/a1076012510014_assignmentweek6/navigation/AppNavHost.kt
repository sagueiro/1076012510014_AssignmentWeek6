package com.vp.a1076012510014_assignmentweek6.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.vp.a1076012510014_assignmentweek6.ui.screen.ClubProjectsScreen
import com.vp.a1076012510014_assignmentweek6.ui.screen.CommunitiesScreen
import com.vp.a1076012510014_assignmentweek6.ui.screen.DashboardScreen
import com.vp.a1076012510014_assignmentweek6.ui.screen.ProjectDetailScreen
import com.vp.a1076012510014_assignmentweek6.viewmodel.CampusViewModel

private fun goToTab(navController: NavHostController, route: Any) {
    navController.navigate(route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val viewModel: CampusViewModel = viewModel()
    val context = LocalContext.current
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val isDashboard = currentDestination?.hierarchy?.any { it.hasRoute(DashboardRoute::class) } == true
    val isCommunities = currentDestination?.hierarchy?.any { it.hasRoute(CommunitiesRoute::class) } == true
    val showBottomBar = isDashboard || isCommunities

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    NavigationBarItem(
                        selected = isDashboard,
                        onClick = { goToTab(navController, DashboardRoute) },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Dashboard") },
                        label = { Text("Dashboard") }
                    )
                    NavigationBarItem(
                        selected = isCommunities,
                        onClick = { goToTab(navController, CommunitiesRoute) },
                        icon = { Icon(Icons.Default.Person, contentDescription = "Communities") },
                        label = { Text("Communities") }
                    )
                }
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = DashboardRoute,
            modifier = Modifier.padding(innerPadding)
        ) {

            // Dashboard
            composable<DashboardRoute> {
                DashboardScreen(
                    joinedCommunities = viewModel.getJoinedCommunities(),
                    projects = viewModel.projects.toList(),
                    communityNameOf = { id -> viewModel.getCommunityName(id) },
                    projectCountOf = { id -> viewModel.countProjects(id) },
                    onProjectClick = { id -> navController.navigate(ProjectDetailRoute(id)) }
                )
            }

            // Communities
            composable<CommunitiesRoute> {
                CommunitiesScreen(
                    communities = viewModel.communities.toList(),
                    onToggleJoin = { id -> viewModel.toggleJoin(id) },
                    onCardClick = { community ->
                        if (community.isJoined) {
                            navController.navigate(ClubProjectsRoute(community.id))
                        } else {
                            // gate akses
                            Toast.makeText(
                                context,
                                "Harap bergabung ke komunitas terlebih dahulu!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                )
            }

            // Club Project
            composable<ClubProjectsRoute> { entry ->
                val route = entry.toRoute<ClubProjectsRoute>()

                ClubProjectsScreen(
                    community = viewModel.getCommunityById(route.communityId),
                    projects = viewModel.getProjectsByCommunity(route.communityId),
                    onBack = { navController.popBackStack() },
                    onProjectClick = { id -> navController.navigate(ProjectDetailRoute(id)) },
                    onDelete = { id -> viewModel.deleteProject(id) },
                    onAddProject = { title, desc, stack ->
                        viewModel.addProject(route.communityId, title, desc, stack)
                    }
                )
            }

            composable<ProjectDetailRoute> { entry ->
                val route = entry.toRoute<ProjectDetailRoute>()
                val project = viewModel.getProjectById(route.projectId)

                var communityName = ""
                if (project != null) {
                    communityName = viewModel.getCommunityName(project.communityId)
                }

                ProjectDetailScreen(
                    project = project,
                    communityName = communityName,
                    onBack = { navController.popBackStack() },
                    onToggleStatus = { viewModel.toggleProjectStatus(route.projectId) }
                )
            }
        }
    }
}