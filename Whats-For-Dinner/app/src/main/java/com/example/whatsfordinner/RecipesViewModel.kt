package com.example.whatsfordinner

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

// data model for recipe
data class Recipe(
    val id: Int,
    val title: String,
    val ingredients: List<String>,
    val steps: List<String>
)

class RecipesViewModel: ViewModel() {
    // observable list of recipes
    private val _recipes = mutableStateListOf<Recipe>()
    val recipes: List<Recipe> get() = _recipes

    init {
        // initial sample recipe data
        _recipes.addAll(
            listOf(
                Recipe(1, "Pancakes", listOf("Flour", "Milk", "Eggs", "Sugar"), listOf("Mix ingredients", "Cook on skillet")),
                Recipe(2, "Tomato Pasta", listOf("Pasta", "Tomato Sauce"), listOf("Boil pasta", "Mix sauce")),
            )
        )
    }

    // function to add a new recipe to the list
    fun addRecipe(title: String, ingredients: List<String>, steps: List<String>): Int {
        val id = (if (_recipes.isEmpty()) 1 else (_recipes.maxOf { it.id } + 1))
        val r = Recipe(id, title, ingredients, steps)
        _recipes.add(r)
        return id
    }

    // function to get a recipe from the list by id
    fun getById(id: Int): Recipe? = _recipes.find { it.id == id }
}