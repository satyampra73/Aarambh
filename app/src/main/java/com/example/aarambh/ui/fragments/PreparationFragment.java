package com.example.aarambh.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aarambh.R;
import com.example.aarambh.ui.activities.SubCategoryActivity;
import com.example.aarambh.ui.adapter.CategoryAdapter;
import com.example.aarambh.data.modelclass.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class PreparationFragment extends Fragment implements CategoryAdapter.OnCategoryClickListener {

    private RecyclerView recyclerView;

    // Override onCreateView instead of onCreate
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_preparation, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recycle_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Load static data
        List<CategoryModel> categories = loadCategories();

        // Set up adapter with click listener
        CategoryAdapter adapter = new CategoryAdapter(categories, this);
        recyclerView.setAdapter(adapter);

        return view;
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

    // Handle item clicks - Implementing the listener
    @Override
    public void onCategoryClick(CategoryModel category) {
        // Navigate to SubCategoryActivity
        Intent intent = new Intent(getActivity(), SubCategoryActivity.class); // Use getActivity() here
        intent.putExtra("CATEGORY_NAME", category.getName());
        intent.putExtra("flag","2");
        startActivity(intent);
    }
}
