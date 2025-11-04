package com.example.mydailyhub

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

// view model for notes screen
class NotesViewModel: ViewModel() {
    // backing field for list of notes
    private val _notes = mutableStateListOf<String>()

    // public access to the notes
    val notes: List<String> get() = _notes

    // function to add new note to list
    fun add(note: String) {
        if (note.isNotBlank()) {
            _notes.add(0, note.trim())
        }
    }

    // function to remove a note at a given index
    fun removeAt(idx: Int) {
        if (_notes[idx].isNotBlank()) _notes.removeAt(idx)
    }

    // function to clear all notes
    fun clear() {
        _notes.clear()
    }
}