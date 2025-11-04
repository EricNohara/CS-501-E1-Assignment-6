package com.example.exploreboston.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.exploreboston.Location

// list screen which shows the items for a given category
@Composable
fun ListScreen(
    category: String,
    items: List<Location>,
    onItemClick: (Int) -> Unit,
    onHome: () -> Unit,
    onBack: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // top bar with back and home buttons
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("All $category", style = MaterialTheme.typography.headlineSmall)
            Row {
                Button(onClick = onBack) { Text("Back") }
                Button(onClick = onHome) { Text("Home") }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // fallback if there are no locations within a given category
        if (items.isEmpty()) {
            Text("No locations in this category.", modifier = Modifier.padding(8.dp))
        } else {
            LazyColumn {
                items(items) { loc ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            // when clicked, display the details for the location
                            .clickable { onItemClick(loc.id) }
                    ) {
                        Row(modifier = Modifier.padding(12.dp)) {
                            Text(loc.name, style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
            }
        }
    }
}