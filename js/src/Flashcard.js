import React, { useState } from 'react';

function Flashcard({ flashcard, deleteFlashcard }) {
  const [flip, setFlip] = useState(false);

  return (
    <div
      onClick={() => setFlip(!flip)}
      className={`card ${flip ? 'flip' : ''}`}
    >
      <div className="front">
        <pre>{flashcard.question}</pre>
        <button
          onClick={(e) => {
            e.stopPropagation(); // Prevent triggering flip on delete
            deleteFlashcard(flashcard.id);
          }}
          className="delete-button"
        >
          Delete
        </button>
      </div>
      <div className="back">
        <pre>{flashcard.answer}</pre>
      </div>
    </div>
  );
}

export default Flashcard;