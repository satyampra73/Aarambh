package com.example.aarambh.activities;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.aarambh.R;
import com.example.aarambh.adapter.OptionsAdapter;
import com.example.aarambh.adapter.QuestionAdapter;
import com.example.aarambh.databinding.ActivityQuizBinding;
import com.example.aarambh.modelclass.QuestionModel;

import java.util.Arrays;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private ActivityQuizBinding binding;
    private int currentQuestion = 0;

    // List of questions to display
    private final List<QuestionModel> questions = Arrays.asList(
            new QuestionModel("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Madrid"}, "Paris", 1, ""),
            new QuestionModel("What is the largest planet in our Solar System?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, "Jupiter", 2, "")
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Using DataBinding to set the layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);

//        // Setting up the RecyclerView for questions
//        setupQuestionRecyclerView();
//
//        // Set up the options RecyclerView for the first question
//        setUpOptionsRecyclerView();

        // Set button listeners for navigation
        setButtonListeners();
    }

    /**
     * Set up the RecyclerView to display questions.
     */
    private void setupQuestionRecyclerView() {
        QuestionAdapter questionAdapter = new QuestionAdapter(questions);
        binding.questionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.questionRecyclerView.setAdapter(questionAdapter);
    }

    /**
     * Set up the RecyclerView to display options for the current question.
     */
    private void setUpOptionsRecyclerView() {
        // Convert the options array to a List for easier handling
        List<String> options = Arrays.asList(questions.get(currentQuestion).getOptions());

        // Initialize the OptionsAdapter with options and click listener
        OptionsAdapter optionsAdapter = new OptionsAdapter(options, this::checkAnswer);
        binding.optionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.optionsRecyclerView.setAdapter(optionsAdapter);
    }

    /**
     * Set up listeners for skip and next buttons.
     */
    private void setButtonListeners() {
        binding.btnSkip.setOnClickListener(v -> skipQuestion());
        binding.btnNext.setOnClickListener(v -> nextQuestion());
    }

    /**
     * Check the selected answer and provide feedback (if needed).
     * @param selectedAnswer The selected option.
     */
    private void checkAnswer(String selectedAnswer) {
        disableOptions(); // Optionally disable the options after selection

        if (selectedAnswer.equals(questions.get(currentQuestion).getCorrectAnswer())) {
            // Correct answer - UI feedback can be added here
        } else {
            // Incorrect answer - UI feedback can be added here
        }

        // Move to next question after a short delay for feedback
        new Handler().postDelayed(this::nextQuestion, 1000);
    }

    /**
     * Disable options after answer selection.
     */
    private void disableOptions() {
        // Logic to disable options, if required (e.g., to prevent re-selection)
    }

    /**
     * Skip the current question and move to the next one.
     */
    private void skipQuestion() {
        nextQuestion();
    }

    /**
     * Move to the next question or end the quiz if it's the last question.
     */
    private void nextQuestion() {
        if (currentQuestion < questions.size() - 1) {
            currentQuestion++;  // Increment to the next question
            setUpOptionsRecyclerView();  // Update options for the new question
        } else {
            endQuiz();  // End the quiz if all questions are answered
        }
    }

    /**
     * End the quiz and show results.
     */
    private void endQuiz() {
        // Logic to show quiz results or an end screen can be implemented here
    }
}
