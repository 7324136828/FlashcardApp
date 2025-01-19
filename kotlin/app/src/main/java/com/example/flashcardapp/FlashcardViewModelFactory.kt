package com.example.flashcardapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flashcardapp.storage.FlashcardStorage

class FlashcardViewModelFactory(
    private val storage: FlashcardStorage
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlashcardViewModel::class.java)) {
            return FlashcardViewModel(storage) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}