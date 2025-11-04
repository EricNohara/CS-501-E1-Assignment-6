package com.example.exploreboston.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.exploreboston.Location

// screen to show the details for a given location in the tour app
@Composable
fun DetailScreen(
    category: String,
    location: Location?,
    onHome: () -> Unit,
    onBack: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // top bar with back and home buttons
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(location?.name ?: "Not found", style = MaterialTheme.typography.headlineSmall)
            Row {
                Button(onClick = onBack) { Text("Back") }
                Button(onClick = onHome) { Text("Home") }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // fallback if there is no location provided as argument
        if (location == null) {
            Text("Location not found for category $category")
        } else {
            // display simple information about the given location
            Text("Category: ${location.category}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(location.description)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}