package com.example.exploreboston

import androidx.lifecycle.ViewModel

// simple data class for a location to be put in the tour app
data class Location(val id: Int, val name: String, val description: String, val category: String)

// view model for tour state
class TourViewModel : ViewModel() {
    // categories
    val categories = listOf("Museums", "Parks", "Restaurants")

    // add some sample locations to view model to display
    private val locations = listOf(
        Location(1, "MIT Museum", "A museum at MIT", "Museums"),
        Location(2, "MFA", "Museum of Fine Arts", "Museums"),
        Location(3, "Boston Common", "Park in Boston", "Parks"),
        Location(4, "El Jefe's", "Mexican restaurant in Boston", "Restaurants"),
        Location(5, "Chipotle", "The one on campus", "Restaurants")
    )

    // helper function to get the items for a category
    fun itemsForCategory(category: String): List<Location> =
        locations.filter { it.category == category }

    // helper function to get the location from a category and id input
    fun getLocation(category: String, id: Int): Location? =
        locations.find { it.category == category && it.id == id }
}