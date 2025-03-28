package com.example.aarambh.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.databinding.DataBindingUtil;

import com.example.aarambh.R;
import com.example.aarambh.ui.activities.ContactUsActivity;
import com.example.aarambh.ui.activities.UpdateDetailsActivity;
import com.example.aarambh.ui.activities.WalletActivity;
import com.example.aarambh.databinding.FragmentProfileBinding;
import com.example.aarambh.ui.activities.AllSubscriptionsActivity;

public class ProfileFragment extends Fragment {

    // Binding object to handle view elements
    private FragmentProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout using DataBindingUtil
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);

        // Set up click listeners
        setupClickListeners();

        // Return the root view of the binding
        return binding.getRoot();
    }


    // Sets up click listeners for buttons and layouts in the fragment.

    private void setupClickListeners() {
        // Edit Profile button
        binding.editProfile.setOnClickListener(v -> {
            if (getActivity() != null) {
                Intent intent = new Intent(getActivity(), UpdateDetailsActivity.class);
                startActivity(intent);
            }
        });


        binding.layoutwallet.setOnClickListener(v -> {
            if (getActivity() != null) {
                Intent intent = new Intent(getActivity(), WalletActivity.class);
                startActivity(intent);
            }
        });
        // Set click listener for layoutSubscription
        binding.layoutSubscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to WalletActivity
                Intent intent = new Intent(getActivity(), AllSubscriptionsActivity.class);
                startActivity(intent);
            }
        });

        // Contact Us layout
        binding.layoutContactus.setOnClickListener(v -> {
            if (getActivity() != null) {
                Intent intent = new Intent(getActivity(), ContactUsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Nullify the binding to avoid memory leaks
        binding = null;
    }
}
