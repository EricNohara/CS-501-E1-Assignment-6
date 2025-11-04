package com.example.mydailyhub

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// screen for displaying notes (takes notes view model as input)
@Composable
fun NotesScreen(notesVM: NotesViewModel) {
    // local state for saving inputted note text
    var text by remember { mutableStateOf("") }

    // list of notes in the view model
    val notes by remember { derivedStateOf { notesVM.notes } }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // screen title
        Text("Notes", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        // note input
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.weight(1f).padding(8.dp),
                placeholder = { Text("Enter a note") },
                singleLine = true
            )
            Spacer(modifier = Modifier.width(8.dp))
            // when clicked, add the inputted note to the view model
            Button(onClick = {
                notesVM.add(text)
                text = ""
            }) { Text("Add") }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // placeholder if there are no notes in the view model
        if (notes.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                Text("No notes yet.")
            }
        } else {
            // lazy list of notes, with their text and a delete button
            LazyColumn {
                itemsIndexed(notes) { idx, note ->
                    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                            Text(note, modifier = Modifier.weight(1f))
                            // button for deleting the note
                            IconButton(onClick = { notesVM.removeAt(idx) }) {
                                Icon(Icons.Default.Delete, contentDescription = "delete")
                            }
                        }
                    }
                }
            }
        }
    }
}