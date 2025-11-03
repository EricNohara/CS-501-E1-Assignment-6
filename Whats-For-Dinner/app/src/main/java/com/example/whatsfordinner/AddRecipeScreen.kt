package com.example.whatsfordinner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

// screen with the add recipe form
// onSave: callback when the user saves the recipe
// onCancel: callback when the user cancels the add recipe
@Composable
fun AddRecipeScreen(onSave: (title: String, ingredients: List<String>, steps: List<String>) -> Unit, onCancel: () -> Unit) {
    // use to hide the keyboard when saving
    val focusManager = LocalFocusManager.current

    // local state for saving user input
    var title by remember { mutableStateOf("") }
    var ingredientsText by remember { mutableStateOf("") } // newline or comma separated
    var stepsText by remember { mutableStateOf("") } // newline separated

    // column layout for form
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        // title
        Text("Add New Recipe", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))

        // recipe title input
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
        )

        Spacer(Modifier.height(8.dp))

        // ingredients input field
        OutlinedTextField(
            value = ingredientsText,
            onValueChange = { ingredientsText = it },
            label = { Text("Ingredients (comma or newline separated)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        // steps input field
        OutlinedTextField(
            value = stepsText,
            onValueChange = { stepsText = it },
            label = { Text("Steps (comma or newline separated)") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 6
        )

        Spacer(modifier = Modifier.height(12.dp))

        // buttons row
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            // save button
            Button(onClick = {
                // hide the keyboard
                focusManager.clearFocus()

                // convert string inputs to list of strings
                val ingredients = splitInput(ingredientsText)
                val steps = splitInput(stepsText)

                // ensure the title is not blank
                if (title.isBlank()) {
                    return@Button
                }

                // call the on save callback to save the recipe to view model
                onSave(title.trim(), ingredients, steps)
            }) {
                Text("Save")
            }

            // cancel button which calls the on cancel callback
            OutlinedButton(onClick = onCancel) {
                Text("Cancel")
            }
        }
    }
}

// helper: split by newline or comma
fun splitInput(input: String, delimiterNewlineOnly: Boolean = false): List<String> {
    if (input.isBlank()) return emptyList()
    return if (delimiterNewlineOnly) {
        input.lines().map { it.trim() }.filter { it.isNotEmpty() }
    } else {
        // split by newline or comma
        input.split(Regex("[,\n]")).map { it.trim() }.filter { it.isNotEmpty() }
    }
}