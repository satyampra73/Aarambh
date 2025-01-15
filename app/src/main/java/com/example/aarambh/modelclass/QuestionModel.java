package com.example.aarambh.modelclass;

/**
 * A model class representing a question in the quiz.
 * Each question consists of a question text, a list of options, the correct answer, a question number, and an optional image URL.
 */
public class QuestionModel {

    // Variables to hold question text, options, correct answer, question number, and image URL
    private String questionText;
    private String[] options;
    private String correctAnswer;
    private int questionNumber;
    private String imageUrl;

    /**
     * Constructor to initialize the QuestionModel with question details.
     *
     * @param questionText   The text of the question.
     * @param options        The list of possible options for the question.
     * @param correctAnswer  The correct answer for the question.
     * @param questionNumber The question number.
     * @param imageUrl       The URL of the question image (optional, can be null).
     */
    public QuestionModel(String questionText, String[] options, String correctAnswer, int questionNumber, String imageUrl) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.questionNumber = questionNumber;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters

    /**
     * Get the text of the question.
     *
     * @return The question text.
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Set the text of the question.
     *
     * @param questionText The question text.
     */
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    /**
     * Get the options for the question.
     *
     * @return An array of options.
     */
    public String[] getOptions() {
        return options;
    }

    /**
     * Set the options for the question.
     *
     * @param options The list of options.
     */
    public void setOptions(String[] options) {
        this.options = options;
    }

    /**
     * Get the correct answer for the question.
     *
     * @return The correct answer.
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Set the correct answer for the question.
     *
     * @param correctAnswer The correct answer.
     */
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * Get the question number.
     *
     * @return The question number.
     */
    public int getQuestionNumber() {
        return questionNumber;
    }

    /**
     * Set the question number.
     *
     * @param questionNumber The question number.
     */
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    /**
     * Get the URL of the question image.
     *
     * @return The image URL (can be null if not applicable).
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Set the URL of the question image.
     *
     * @param imageUrl The image URL (can be null if not applicable).
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
