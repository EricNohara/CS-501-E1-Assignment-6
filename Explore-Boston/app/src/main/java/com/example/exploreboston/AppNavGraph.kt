package com.example.exploreboston

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import com.example.exploreboston.screens.CategoriesScreen
import com.example.exploreboston.screens.DetailScreen
import com.example.exploreboston.screens.HomeScreen
import com.example.exploreboston.screens.ListScreen

// sealed routes for navigation
sealed class Routes(val route: String) {
    // static routes
    object Home : Routes("home")
    object Categories : Routes("categories")

    // dynamic routes
    object List : Routes("list/{category}") {
        fun createRoute(category: String) = "list/$category"
    }
    object Detail : Routes("detail/{category}/{id}") {
        fun createRoute(category: String, id: Int) = "detail/$category/$id"
    }
}

// apps navigation graph
@Composable
fun AppNavGraph(tourViewModel: TourViewModel, modifier: Modifier = Modifier) {
    // used for navigation
    val navController = rememberNavController()

    // navigation helpers
    val onHome: () -> Unit = {
        // navigate to home and clear back stack
        navController.navigate(Routes.Home.route) {
            popUpTo(navController.graph.startDestinationId) { inclusive = true }
        }
    }

    val navigate: (String) -> Unit = { route ->
        // navigate to any route
        navController.navigate(route)
    }

    val goBack: () -> Unit = {
        // used to go back in the stack with back button
        navController.popBackStack()
    }

    Scaffold(topBar = { ReusableTopBar(title = "Explore Boston") }) { innerPadding ->
        // ensure NavHost recomposes when backStackEntry changes to avoid lambdas not refreshing
        key(navController.currentBackStackEntryAsState().value?.id ?: "root") {
            NavHost(
                navController = navController,
                startDestination = Routes.Home.route,
                modifier = modifier.padding(innerPadding)
            ) {
                // home screen
                composable(Routes.Home.route) {
                    HomeScreen(onCategoriesClick = { navigate(Routes.Categories.route) })
                }

                // categories screen
                composable(Routes.Categories.route) { backStackEntry ->
                    CategoriesScreen(
                        categories = tourViewModel.categories,
                        onCategorySelected = { category ->
                            navigate(Routes.List.createRoute(category))
                        },
                        onHome = onHome,
                        onBack = goBack
                    )
                }

                // list of items for a category
                composable(
                    route = Routes.List.route,
                    arguments = listOf(navArgument("category") { type = NavType.StringType })
                ) { backStackEntry ->
                    val category = backStackEntry.arguments?.getString("category") ?: return@composable
                    ListScreen(
                        category = category,
                        items = tourViewModel.itemsForCategory(category),
                        onItemClick = { id -> navigate(Routes.Detail.createRoute(category, id)) },
                        onHome = onHome,
                        onBack = goBack
                    )
                }

                // details screen
                composable(
                    route = Routes.Detail.route,
                    arguments = listOf(
                        navArgument("category") { type = NavType.StringType },
                        navArgument("id") { type = NavType.IntType }
                    )
                ) { backStackEntry ->
                    val category = backStackEntry.arguments?.getString("category") ?: return@composable
                    val id = backStackEntry.arguments?.getInt("id") ?: return@composable
                    DetailScreen(
                        category = category,
                        location = tourViewModel.getLocation(category, id),
                        onHome = onHome,
                        onBack = goBack
                    )
                }
            }
        }
    }
}
