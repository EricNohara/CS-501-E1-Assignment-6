package com.example.mydailyhub

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

// data class storing the fields for a given task
data class Task (
    val id: Int,
    val title: String,
    var done: Boolean = false
)

class TasksViewModel: ViewModel() {
    // backing field
    private val _tasks = mutableStateListOf<Task>()

    // public tasks field
    val tasks: List<Task> get() = _tasks

    fun add(title: String) {
        // do not allow blank titles
        if (title.isBlank()) return

        // get the next task id (auto incremented from most recent task) or 1 if there are no tasks
        val id = if (_tasks.isEmpty()) 1 else (_tasks.maxOf { it.id } + 1)

        // add the task
        _tasks.add(0, Task(id, title.trim()))
    }

    // function to set a task as done
    fun toggleDone(id: Int) {
        // get the index of the task with given id
        val idx = _tasks.indexOfFirst { it.id == id }

        // toggle the done attribute in the given task
        if (idx >= 0) {
            val t = _tasks[idx]
            _tasks[idx] = t.copy(done = !t.done)
        }
    }

    // function to remove a task
    fun remove(id: Int) {
        _tasks.removeAll { it.id == id }
    }
}