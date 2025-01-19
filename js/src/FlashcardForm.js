import React, { useState } from 'react';

function FlashcardForm({ addFlashcard }) {
  const [question, setQuestion] = useState('');
  const [answer, setAnswer] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    addFlashcard({ question, answer });
    setQuestion('');
    setAnswer('');
  };

  const adjustHeight = (e) => {
    e.target.style.height = 'auto'; // Reset height
    e.target.style.height = `${e.target.scrollHeight}px`; // Adjust height based on content
  };

  return (
    <form onSubmit={handleSubmit} className="flashcard-form">
      <textarea
        placeholder="Question"
        value={question}
        onChange={(e) => setQuestion(e.target.value)}
        onInput={adjustHeight}
        required
      />
      <textarea
        placeholder="Answer"
        value={answer}
        onChange={(e) => setAnswer(e.target.value)}
        onInput={adjustHeight}
        required
      />
      <button type="submit">Add Flashcard</button>
    </form>
  );
}

export default FlashcardForm;