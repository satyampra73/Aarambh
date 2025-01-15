package com.example.aarambh.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.aarambh.R;
import com.example.aarambh.activities.UpdateDetailsActivity;

public class ProfileFragment extends Fragment {

    private ImageView editProfileButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment's layout
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize the ImageView for edit_profile
        editProfileButton = view.findViewById(R.id.edit_profile);

        // Set a click listener on the edit_profile ImageView
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the EditProfileActivity
                Intent intent = new Intent(getActivity(), UpdateDetailsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
