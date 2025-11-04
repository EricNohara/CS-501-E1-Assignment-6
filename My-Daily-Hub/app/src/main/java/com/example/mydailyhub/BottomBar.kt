package com.example.mydailyhub

import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem

// sealed routes for the bottom bar
sealed class Screen(val route: String, val label: String, val icon: @Composable () -> Unit) {
    // include optional query param for animation: ?anim={anim}
    object Notes : Screen("notes?anim={anim}", "Notes", { Icon(Icons.Default.Note, contentDescription = "Notes") })
    object Tasks : Screen("tasks?anim={anim}", "Tasks", { Icon(Icons.Default.CheckBox, contentDescription = "Tasks") })
    object Calendar : Screen("calendar?anim={anim}", "Calendar", { Icon(Icons.Default.CalendarToday, contentDescription = "Calendar") })

    companion object {
        // helper to create route with anim param allowing for easy appending different animations
        fun create(routeBase: String, anim: String = "fade"): String {
            return "$routeBase?anim=$anim"
        }
    }
}

// composable for displaying the bottom navigation bar
// navController for navigation actions, currentRoute used to highlight current route
@Composable
fun BottomBar(navController: NavHostController, currentRoute: String?) {
    // the items to display in the bottom navigation bar
    val items = listOf(
        Pair("notes", Screen.Notes),
        Pair("tasks", Screen.Tasks),
        Pair("calendar", Screen.Calendar)
    )

    NavigationBar {
        items.forEach { (baseRoute, screen) ->
            // set the nav item that is currently routed to as selected in the bar
            val selected = currentRoute == baseRoute

            NavigationBarItem(
                icon = { screen.icon() },
                label = { Text(screen.label) },
                selected = selected,
                onClick = {
                    // navigate with save/restore state to avoid re-creating screens and preserve state
                    navController.navigate(Screen.create(baseRoute, anim = "slide")) {
                        // pop up to start destination to avoid building a large stack
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }

                        // Avoid multiple copies
                        launchSingleTop = true

                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}