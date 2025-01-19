import React, { useState } from 'react';
import FlashcardForm from './FlashcardForm';
import FlashcardCarousel from './FlashcardCarousel';
import { v4 as uuidv4 } from 'uuid';
import useLocalStorage from './useLocalStorage';
import './App.css';


function App() {
  const [flashcards, setFlashcards] = useLocalStorage('flashcards', []);


  const addFlashcard = ({ question, answer }) => {
    setFlashcards([...flashcards, { id: uuidv4(), question, answer }]);
  };

  const deleteFlashcard = (id) => {
    setFlashcards(flashcards.filter((flashcard) => flashcard.id !== id));
  };

  return (
    <div className="app">
      <h1>Flashcard App</h1>
      <FlashcardForm addFlashcard={addFlashcard} />
      <FlashcardCarousel
        flashcards={flashcards}
        deleteFlashcard={deleteFlashcard}
      />
    </div>
  );
}

export default App;