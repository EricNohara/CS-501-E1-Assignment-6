package com.example.exploreboston

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // set content to the app
        setContent {
            MaterialTheme {
                // shared view model for NavGraph and screens
                val vm: TourViewModel = viewModel()

                // launch the navigation graph with view model
                AppNavGraph(tourViewModel = vm)
            }
        }
    }
}