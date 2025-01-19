package com.example.flashcardapp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.flashcardapp.model.Flashcard
import com.example.flashcardapp.storage.FlashcardStorage
import java.util.UUID
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FlashcardApp(storage: FlashcardStorage) {
    val viewModel: FlashcardViewModel = viewModel(factory = FlashcardViewModelFactory(storage))
    val flashcards by viewModel.flashcards.collectAsState(initial = emptyList())

    var question by remember { mutableStateOf("") }
    var answer by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "Flashcard App", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Input Form
        BasicTextField(
            value = question,
            onValueChange = { question = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(4.dp))
                .padding(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.padding(8.dp)) {
                    if (question.isEmpty()) Text("Question")
                    innerTextField()
                }
            }
        )
        BasicTextField(
            value = answer,
            onValueChange = { answer = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(4.dp))
                .padding(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.padding(8.dp)) {
                    if (answer.isEmpty()) Text("Answer")
                    innerTextField()
                }
            }
        )
        Button(
            onClick = {
                viewModel.addFlashcard(Flashcard(UUID.randomUUID().toString(), question, answer))
                question = ""
                answer = ""
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Add Flashcard")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Carousel
        FlashcardCarousel(flashcards, onDelete = { viewModel.deleteFlashcard(it) })
    }
}