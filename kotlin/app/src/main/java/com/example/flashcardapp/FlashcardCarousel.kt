package com.example.flashcardapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flashcardapp.model.Flashcard
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.wajahatkarim.flippable.Flippable
import com.wajahatkarim.flippable.rememberFlipController

@Composable
fun FlashcardCarousel(flashcards: List<Flashcard>, onDelete: (Flashcard) -> Unit) {
    if (flashcards.isEmpty()) {
        Text("No flashcards available.")
    } else {
        LazyRow(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(flashcards.size) { index ->
                val flashcard = flashcards[index]
                FlashcardCard(flashcard, onDelete)
            }
        }
    }
}



@Composable
fun FlashcardCard(flashcard: Flashcard, onDelete: (Flashcard) -> Unit) {
    val flipController = rememberFlipController()

    Flippable(
        frontSide = {
            FlashcardCardSide(content = flashcard.question,
            onDelete = {
                onDelete(flashcard)
            })
        },
        backSide = {
            FlashcardCardSide(content = flashcard.answer,
                onDelete = {
                    onDelete(flashcard)
            })
        },
        flipController = flipController,
        flipDurationMs = 400,
        flipOnTouch = true,
        modifier = Modifier
            .width(300.dp)
            .padding(16.dp)
    )
}

@Composable
fun FlashcardCardSide(content: String, onDelete: () -> Unit) {

    Card(
        modifier = Modifier
            .width(300.dp)
            .padding(16.dp), // Toggle flip state on tap
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Display either the question or the answer based on the flip state
            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Delete button
            Button(onClick = { onDelete() }) {
                Text("Delete")
            }
        }
    }
}


