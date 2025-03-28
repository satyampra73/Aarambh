package com.example.aarambh.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aarambh.R;

import java.util.List;

public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.OptionViewHolder> {

    private List<String> options;
    private OnOptionClickListener onOptionClickListener;

    // Constructor to initialize options list and click listener
    public OptionsAdapter(List<String> options, OnOptionClickListener onOptionClickListener) {
        this.options = options;
        this.onOptionClickListener = onOptionClickListener;
    }

    // Interface for option click listener
    public interface OnOptionClickListener {
        void onOptionClick(String selectedOption);
    }

    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each option item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_option, parent, false);
        return new OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionViewHolder holder, int position) {
        String option = options.get(position);
        holder.optionTextView.setText(option);

        // Handle option click
        holder.itemView.setOnClickListener(v -> onOptionClickListener.onOptionClick(option));
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    // Method to update options (if needed)
    public void updateOptions(List<String> newOptions) {
        this.options = newOptions;
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

    // ViewHolder class to hold the views for each option item
    public static class OptionViewHolder extends RecyclerView.ViewHolder {
        TextView optionTextView;

        public OptionViewHolder(@NonNull View itemView) {
            super(itemView);
            optionTextView = itemView.findViewById(R.id.optionTextView);
        }
    }
}
