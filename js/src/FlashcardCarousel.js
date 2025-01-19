import React, { useState } from 'react';

function FlashcardCarousel({ flashcards, deleteFlashcard }) {
  const [currentIndex, setCurrentIndex] = useState(0);

  const handleNext = () => {
    setCurrentIndex((prevIndex) => (prevIndex + 1) % flashcards.length);
  };

  const handlePrev = () => {
    setCurrentIndex((prevIndex) =>
      prevIndex === 0 ? flashcards.length - 1 : prevIndex - 1
    );
  };

  if (flashcards.length === 0) {
    return <p>No flashcards available. Add some to get started!</p>;
  }

  return (
    <div className="carousel">
      <button className="carousel-button prev" onClick={handlePrev}>
        &lt;
      </button>
      <div className="carousel-card">
        <div className="card">
          <div className="front">
            <pre>{flashcards[currentIndex].question}</pre>
            <button
              className="delete-button"
              onClick={() => deleteFlashcard(flashcards[currentIndex].id)}
            >
              Delete
            </button>
          </div>
          <div className="back">
            <pre>{flashcards[currentIndex].answer}</pre>
          </div>
        </div>
      </div>
      <button className="carousel-button next" onClick={handleNext}>
        &gt;
      </button>
    </div>
  );
}

export default FlashcardCarousel;