import React from 'react';
import Flashcard from './Flashcard';

function FlashcardList({ flashcards, deleteFlashcard }) {
  return (
    <div className="card-grid">
      {flashcards.map(flashcard => (
        <Flashcard
        key={flashcard.id}
        flashcard={flashcard}
        deleteFlashcard={deleteFlashcard}
      />
      ))}
    </div>
  );
}

export default FlashcardList;