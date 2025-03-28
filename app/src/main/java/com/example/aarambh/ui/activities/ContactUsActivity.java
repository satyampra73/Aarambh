package com.example.aarambh.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.aarambh.R;
import com.example.aarambh.databinding.ActivityContactUsBinding;

public class ContactUsActivity extends AppCompatActivity {

    private ActivityContactUsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Bind the layout using DataBindingUtil
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_us);

        // Set up the toolbar with navigation
        setUpToolbar();

        // Set up the send button click listener
        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSendButtonClick();
            }
        });
    }


     // Set up the Toolbar with a back button and title.

    private void setUpToolbar() {
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Contact Us");
        }

        // Handle back button navigation
        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }


     // Handles the logic when the send button is clicked.
    private void handleSendButtonClick() {
        String name = binding.etName.getText().toString().trim();
        String message = binding.etMessage.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (message.isEmpty()) {
            Toast.makeText(this, "Please enter your message", Toast.LENGTH_SHORT).show();
            return;
        }

        // Simulate sending the message
        Toast.makeText(this, "Message sent successfully!", Toast.LENGTH_SHORT).show();

        // Optionally clear the input fields
        binding.etName.setText("");
        binding.etMessage.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Nullify binding to avoid memory leaks
        binding = null;
    }
}
