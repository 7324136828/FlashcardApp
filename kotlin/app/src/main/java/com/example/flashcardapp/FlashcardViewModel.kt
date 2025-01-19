package com.example.flashcardapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcardapp.model.Flashcard
import com.example.flashcardapp.storage.FlashcardStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FlashcardViewModel(private val storage: FlashcardStorage) : ViewModel() {
    private val _flashcards = MutableStateFlow<List<Flashcard>>(emptyList())
    val flashcards: StateFlow<List<Flashcard>> = _flashcards

    init {
        viewModelScope.launch {
            storage.flashcards.collect { _flashcards.value = it }
        }
    }

    fun addFlashcard(flashcard: Flashcard) {
        viewModelScope.launch {
            val updatedList = _flashcards.value.toMutableList().apply { add(flashcard) }
            storage.saveFlashcards(updatedList)
        }
    }

    fun deleteFlashcard(flashcard: Flashcard) {
        viewModelScope.launch {
            val updatedList = _flashcards.value.toMutableList().apply { remove(flashcard) }
            storage.saveFlashcards(updatedList)
        }
    }
}