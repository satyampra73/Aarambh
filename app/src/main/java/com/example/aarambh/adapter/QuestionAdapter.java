package com.example.aarambh.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aarambh.R;
import com.example.aarambh.modelclass.QuestionModel;

import java.util.Arrays;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private final List<QuestionModel> questions;

    // Constructor to initialize the list of questions
    public QuestionAdapter(List<QuestionModel> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each question item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        // Get the current question
        QuestionModel question = questions.get(position);

        // Set the question text and number
        holder.questionTextView.setText(question.getQuestionText());
        holder.questionNumberTextView.setText("Question No: " + (position + 1)); // Display 1-based index

        // Load question image if available
//        if (question.getImageUrl() != null && !question.getImageUrl().isEmpty()) {
//            Glide.with(holder.itemView.getContext())
//                    .load(question.getImageUrl())
//                    .placeholder(R.drawable.question_image_placeholder) // Replace with a drawable resource for placeholder
//                    .into(holder.questionImageView);
//            holder.questionImageView.setVisibility(View.VISIBLE); // Ensure visibility if image exists
//        } else {
//            holder.questionImageView.setVisibility(View.GONE); // Hide if no image
//        }

        // Convert options from String[] to List<String>
        List<String> options = Arrays.asList(question.getOptions());

        // Set up options RecyclerView (only if not already set)
        if (holder.optionsRecyclerView.getAdapter() == null) {
            holder.optionsRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
            OptionsAdapter optionsAdapter = new OptionsAdapter(options, option -> {
                // Handle option click logic (if needed)
            });
            holder.optionsRecyclerView.setAdapter(optionsAdapter);
        } else {
            // If the RecyclerView has already been set up, just update the data
            ((OptionsAdapter) holder.optionsRecyclerView.getAdapter()).updateOptions(options);
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    // ViewHolder class to hold the views for each question item
    public static class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView questionNumberTextView, questionTextView;
        ImageView questionImageView;
        RecyclerView optionsRecyclerView;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize views
//            questionNumberTextView = itemView.findViewById(R.id.questionNumberTextView);
//            questionTextView = itemView.findViewById(R.id.questionTextView);
            questionImageView = itemView.findViewById(R.id.questionImageView);
           // optionsRecyclerView = itemView.findViewById(R.id.optionsRecyclerView);
        }
    }
}
