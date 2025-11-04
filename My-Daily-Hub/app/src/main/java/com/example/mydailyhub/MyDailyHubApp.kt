package com.example.mydailyhub

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost

// the main app
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MyDailyHubApp() {
    // nav controller to handle navigation between screens
    val navController = rememberNavController()

    // view models for notes and tasks used to persist UI data
    val notesVM: NotesViewModel = viewModel()
    val tasksVM: TasksViewModel = viewModel()

    // use to highlight active bottom item
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    // extract current base route
    val currentRoute = navBackStackEntry?.destination?.route?.substringBefore("?")

    Scaffold(
        // set the bottom bar as my navigation bar
        bottomBar = { BottomBar(navController = navController, currentRoute = currentRoute) }
    ) { innerPadding ->

        // controls which screen is displayed based on nav state with animations
        NavHost(
            navController = navController,
            // default screen when app starts
            startDestination = Screen.Notes.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Notes screen
            composable(
                route = Screen.Notes.route,
                enterTransition = { slideInHorizontally(tween(300)) },
                exitTransition = { slideOutHorizontally(tween(300)) },
                popEnterTransition = { slideInHorizontally(tween(300)) },
                popExitTransition = { slideOutHorizontally(tween(300)) }
            ) {
                // inject its view model
                NotesScreen(notesVM)
            }

            // Tasks screen
            composable(
                route = Screen.Tasks.route,
                enterTransition = { scaleIn(tween(300)) },
                exitTransition = { scaleOut(tween(300)) },
                popEnterTransition = { scaleIn(tween(300)) },
                popExitTransition = { scaleOut(tween(300)) }
            ) {
                // inject its view model
                TasksScreen(tasksVM)
            }

            // Calendar
            composable(
                route = Screen.Calendar.route,
                enterTransition = { fadeIn(tween(250)) },
                exitTransition = { fadeOut(tween(250)) },
                popEnterTransition = { fadeIn(tween(250)) },
                popExitTransition = { fadeOut(tween(250)) }
            ) {
                // placeholder calendar screen
                CalendarScreen()
            }
        }
    }
}
