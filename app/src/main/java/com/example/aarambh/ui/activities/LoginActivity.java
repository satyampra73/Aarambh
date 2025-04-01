package com.example.aarambh.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aarambh.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        Button continueButton = findViewById(R.id.continueButton);
        TextView forgotPassword = findViewById(R.id.forgotText);

        // Set an OnClickListener on the "Continue" button
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to BottomNavigation screen
                Intent intent = new Intent(LoginActivity.this, CategoryActivity.class);
                startActivity(intent);
                finish(); // Close the Login activity (optional)
            }
        });

        // Set an OnClickListener on the "Forgot Password" text
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ForgotPasswordActivity
                Intent intent = new Intent(LoginActivity.this, MobileVerifyActivity.class);
                startActivity(intent);
            }
        });
    }
}
