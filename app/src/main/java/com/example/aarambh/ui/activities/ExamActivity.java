package com.example.aarambh.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aarambh.R;
import com.example.aarambh.ui.adapter.CategoryAdapter;
import com.example.aarambh.data.modelclass.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class ExamActivity extends AppCompatActivity implements CategoryAdapter.OnCategoryClickListener {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycle_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load static data
        List<CategoryModel> categories = loadCategories();

        // Set up adapter with click listener
        CategoryAdapter adapter = new CategoryAdapter(categories, this);
        recyclerView.setAdapter(adapter);
    }

    private List<CategoryModel> loadCategories() {
        List<CategoryModel> categories = new ArrayList<>();
        categories.add(new CategoryModel("UPSC Civil Services"));
        categories.add(new CategoryModel("SSC CGL"));
        categories.add(new CategoryModel("IBPS PO"));
        categories.add(new CategoryModel("RRB NTPC"));
        categories.add(new CategoryModel("SBI PO"));
        return categories;
    }

    // Handle item clicks
    @Override
    public void onCategoryClick(CategoryModel category) {
        // Navigate to SubCategory activity
        Intent intent = new Intent(this, SubCategoryActivity.class);
        intent.putExtra("CATEGORY_NAME", category.getName());
        intent.putExtra("flag","1");
        startActivity(intent);
    }
}
