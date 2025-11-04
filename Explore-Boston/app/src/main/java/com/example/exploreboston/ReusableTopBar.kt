package com.example.exploreboston

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

// reusable top bar with just a title for now
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReusableTopBar(title: String) {
    TopAppBar(
        title = { Text(title) }
    )
}