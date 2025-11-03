package com.example.whatsfordinner

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

// sealed routes class for storing the routes and their labels to be used by the navigation
sealed class Routes(val route: String, val label: String) {
    // static routes without args
    object Home : Routes("home", "Home")
    object Add : Routes("add", "Add")
    object Settings : Routes("settings", "Settings")

    // dynamic route for recipe details (needs id arg)
    object Detail : Routes("detail/{id}", "Detail") {
        // helper to build the concrete route with id
        fun createRoute(id: Int) = "detail/$id"

        // base route text (without arg) if needed
        const val base = "detail"
    }
}

// composable navigation bar at the bottom of the app
@Composable
fun BottomBar(navController: NavHostController) {
    // navigation items: home, add, settings
    val items = listOf(Routes.Home, Routes.Add, Routes.Settings)

    // get the current route by observing the navigation back stack
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // the navigation bar component
    NavigationBar {
        // create nav item for each item
        items.forEach { screen ->
            // see if current screen should be marked selected
            val selected = currentRoute == screen.route

            NavigationBarItem(
                // set the icon to the correct route's icon
                icon = {
                    when (screen) {
                        Routes.Home -> Icon(Icons.Default.Home, contentDescription = null)
                        Routes.Add -> Icon(Icons.Default.Add, contentDescription = null)
                        Routes.Settings -> Icon(Icons.Default.Settings, contentDescription = null)
                        else -> Icon(Icons.Default.Home, contentDescription = null)
                    }
                },
                // set the label to the route's label
                label = { Text(screen.label) },
                selected = selected,
                // when clicked navigate to the correct route
                onClick = {
                    // navigate with launchSingleTop to avoid multiple copies
                    navController.navigate(screen.route) {
                        launchSingleTop = true

                        // If navigating to Home, clear intermediate destinations from the stack
                        if (screen is Routes.Home) {
                            popUpTo(Routes.Home.route) { inclusive = false }
                        }
                    }
                }
            )
        }
    }
}