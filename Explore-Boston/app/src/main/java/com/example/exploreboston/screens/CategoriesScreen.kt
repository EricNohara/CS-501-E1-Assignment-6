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

// screen to display list of categories
@Composable
fun CategoriesScreen(
    categories: List<String>,
    onCategorySelected: (String) -> Unit,
    onHome: () -> Unit,
    onBack: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // top bar with back and home buttons
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Categories", style = MaterialTheme.typography.headlineSmall)
            Row {
                Button(onClick = onBack) { Text("Back") }
                Button(onClick = onHome) { Text("Home") }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // lazy list of categories defined in the app nav graph
        LazyColumn {
            items(categories) { category ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        // when clicked, navigate to the list of items for that category
                        .clickable { onCategorySelected(category) }
                ) {
                    Row(modifier = Modifier.padding(12.dp)) {
                        Text(category, style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}