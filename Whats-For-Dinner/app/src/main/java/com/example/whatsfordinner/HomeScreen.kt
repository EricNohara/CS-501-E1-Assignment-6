package com.example.whatsfordinner

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// home screen showing the list of clickable recipes
// onRecipeClick is the callback which routes to the correct details page
// onAddPressed routes to the add recipe form page
@Composable
fun HomeScreen(
    recipes: List<Recipe>,
    onRecipeClick: (Int) -> Unit,
    onAddPressed: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // header with the add recipe button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("All Recipes (${recipes.size})", style = MaterialTheme.typography.titleMedium)
            Button(onClick = onAddPressed) { Text("Add") }
        }

        HorizontalDivider()

        // LazyColumn of clickable recipe names
        androidx.compose.foundation.lazy.LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(recipes) { recipe ->
                RecipeListItem(recipe = recipe, onClick = { onRecipeClick(recipe.id) })
            }
        }
    }
}

// composable for a single recipe item in the list
@Composable
fun RecipeListItem(recipe: Recipe, onClick: () -> Unit) {
    // simple column with the recipe title and details about its ingredients and steps
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Text(recipe.title, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text("${recipe.ingredients.size} ingredients — ${recipe.steps.size} steps", style = MaterialTheme.typography.bodySmall)
    }
}