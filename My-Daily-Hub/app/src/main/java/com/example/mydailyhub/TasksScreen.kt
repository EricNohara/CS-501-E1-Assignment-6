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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
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

// screen for displaying all tasks (takes task view model as input)
@Composable
fun TasksScreen(tasksVM: TasksViewModel) {
    // local state for inputted tasks
    var text by remember { mutableStateOf("") }

    // get the list of tasks from view model
    val tasks by remember { derivedStateOf { tasksVM.tasks } }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // screen title
        Text("Tasks", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        // input for new tasks
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.weight(1f).padding(8.dp),
                placeholder = { Text("Enter a task") },
                singleLine = true
            )
            Spacer(modifier = Modifier.width(8.dp))
            // button to add inputted task to view model
            Button(onClick = {
                tasksVM.add(text)
                text = ""
            }) { Text("Add") }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // display placeholder if there are not tasks in the view model
        if (tasks.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                Text("No tasks yet.")
            }
        } else {
            // display lazy list of tasks as checklist items
            LazyColumn {
                itemsIndexed(tasks) { _, task ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // checkbox for the task which sets a task as done if checked
                        Checkbox(checked = task.done, onCheckedChange = { tasksVM.toggleDone(task.id) })
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(task.title, modifier = Modifier.weight(1f))
                        // button to delete checklist item
                        IconButton(onClick = { tasksVM.remove(task.id) }) {
                            Icon(Icons.Default.Delete, contentDescription = "remove")
                        }
                    }
                }
            }
        }
    }
}