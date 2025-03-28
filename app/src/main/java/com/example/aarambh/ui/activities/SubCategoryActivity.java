package com.example.aarambh.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aarambh.R;
import com.example.aarambh.ui.adapter.CategoryAdapter;
import com.example.aarambh.data.modelclass.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private CategoryAdapter adapter;
    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        // Initialize Views
        recyclerView = findViewById(R.id.recycle_subcategory);
        progressBar = findViewById(R.id.progressBar);

        flag = getIntent().getStringExtra("flag");
        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load Static Data
        List<CategoryModel> categories = loadCategories();

        // Initialize Adapter
        adapter = new CategoryAdapter(categories, category -> {
            // Navigate to BottomNavigationActivity
            if (flag.equals("1")){
                Intent intent = new Intent(SubCategoryActivity.this, BottomNavigationActivity.class);
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(SubCategoryActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        // Hide Progress Bar
        progressBar.setVisibility(View.GONE);
    }

    private List<CategoryModel> loadCategories() {
        List<CategoryModel> categories = new ArrayList<>();
        categories.add(new CategoryModel("Maths"));
        categories.add(new CategoryModel("Science"));
        categories.add(new CategoryModel("History"));
        categories.add(new CategoryModel("Geography"));
        categories.add(new CategoryModel("English"));
        return categories;
    }
}
