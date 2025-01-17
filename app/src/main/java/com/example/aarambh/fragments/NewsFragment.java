package com.example.aarambh.fragments;

import static androidx.databinding.DataBindingUtil.setContentView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.aarambh.R;
import com.example.aarambh.adapter.NewsAdapter;
import com.example.aarambh.databinding.FragmentNewsBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsFragment extends Fragment {
    FragmentNewsBinding binding;

    private Map<String, String> dataMap; // Stores data corresponding to items
    private TextView resultTextView; // Displays data of the selected/typed item




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View view=binding.getRoot();

        // Sample data for the adapter
        List<NewsAdapter.News> newsList = new ArrayList<>();
        newsList.add(new NewsAdapter.News("TET exam will be conducted on 12 Dec.", "12 December, 2023", R.drawable.place_holder_icon));
        newsList.add(new NewsAdapter.News("New curriculum announced for 2024", "5 January, 2024", R.drawable.place_holder_icon));
        newsList.add(new NewsAdapter.News("Annual school fest dates released", "10 February, 2024", R.drawable.place_holder_icon));

        // Set adapter with data

        NewsAdapter newsAdapter=new NewsAdapter(newsList);
        binding.recyclerviewOne.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerviewOne.setAdapter(newsAdapter);




        // Initialize views
        SearchView searchView = view.findViewById(R.id.searchView);
        ImageView dropdownIcon = view.findViewById(R.id.dropdownicon);
        resultTextView = view.findViewById(R.id.resultTextView);

        // Initialize data map
        initializeData();


        // Set up the dropdown menu
        dropdownIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getContext(), dropdownIcon);
                popupMenu.getMenuInflater().inflate(R.menu.dropdown_menu, popupMenu.getMenu());

                // Handle menu item clicks
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String selectedItem = item.getTitle().toString();
                        searchView.setQuery(selectedItem, false); // Set the selected item in the search box
                        displayData(selectedItem);
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        // Handle search query submissions
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                displayData(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Optional: Provide live feedback while typing
                return false;
            }
        });

        return view;
    }

    // Initialize data map with sample data
    private void initializeData() {
        dataMap = new HashMap<>();
        dataMap.put("All", "Details about All");
        dataMap.put("TET", "Details about TET");
        dataMap.put("CTET", "Details about CTET");
    }

    // Display data for the selected/typed item
    private void displayData(String item) {
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(item)) {
                resultTextView.setText(entry.getValue());
                return;
            }
        }
        resultTextView.setText("No data found for: " + item);
    }
}

