package com.example.flashcardapp.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.flashcardapp.model.Flashcard
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("flashcards")

class FlashcardStorage(private val context: Context) {
    private val gson = Gson()
    private val flashcardsKey = stringPreferencesKey("flashcards")

    val flashcards: Flow<List<Flashcard>> = context.dataStore.data.map { preferences ->
        val json = preferences[flashcardsKey] ?: "[]"
        gson.fromJson(json, Array<Flashcard>::class.java).toList()
    }

    suspend fun saveFlashcards(flashcards: List<Flashcard>) {
        val json = gson.toJson(flashcards)
        context.dataStore.edit { preferences ->
            preferences[flashcardsKey] = json
        }
    }
}