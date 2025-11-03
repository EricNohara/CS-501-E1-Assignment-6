package com.example.whatsfordinner

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.material3.TopAppBar

// main recipe app containing all of the screens
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesApp(recipesViewModel: RecipesViewModel = viewModel()) {
    // used for navigation
    val navController = rememberNavController()

    // scaffold with consistent top bar and bottom navigation
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("What's for Dinner") })
        },
        bottomBar = {
            // bottom navigation
            BottomBar(navController = navController)
        }
    ) { innerPadding ->
        // defines all screens and handles navigation between them, starting at the home screen
        NavHost(
            navController = navController,
            startDestination = Routes.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Home
            composable(Routes.Home.route) { backStackEntry ->
                HomeScreen(
                    recipes = recipesViewModel.recipes,
                    onRecipeClick = { recipeId ->
                        // when clicked, show the detailed screen for the current recipe id
                        navController.navigate(Routes.Detail.base + "/$recipeId") {
                            // prevent multiple copies of detail when pressed
                            launchSingleTop = true
                        }
                    },
                    onAddPressed = {
                        // when clicked, go to the add recipe screen
                        navController.navigate(Routes.Add.route) {
                            launchSingleTop = true
                        }
                    }
                )
            }

            // Add Recipe
            composable(Routes.Add.route) { backStackEntry ->
                AddRecipeScreen(
                    // callback to perform when save button is clicked
                    onSave = { title, ingredients, steps ->
                        // add the new recipe in the view model
                        val newId = recipesViewModel.addRecipe(title, ingredients, steps)

                        // go back to home page after saving
                        navController.navigate(Routes.Home.route) {
                            // remove Add screen from backstack to avoid returning to it
                            popUpTo(Routes.Home.route) { inclusive = false }
                            // prevent multiple copies of Home on backstack
                            launchSingleTop = true
                        }
                    },
                    // when cancel button is clicked, go back to previous screen on backstack
                    onCancel = {
                        // return to previous
                        navController.popBackStack()
                    }
                )
            }

            // Detail screen
            composable(
                route = Routes.Detail.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                // get the recipe id from the backstack route
                val idArg = backStackEntry.arguments?.getInt("id")

                // get the recipe from view model by id
                val recipe = idArg?.let { recipesViewModel.getById(it) }

                if (recipe != null) {
                    // show the recipe detail screen
                    DetailScreen(recipe = recipe, onBack = { navController.popBackStack() })
                } else {
                    // else just show not found
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                        Text("Recipe not found")
                    }
                }
            }

            // Settings simple placeholder
            composable(Routes.Settings.route) {
                SettingsScreen()
            }
        }
    }
}