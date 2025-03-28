package com.example.aarambh.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.aarambh.R;
import com.example.aarambh.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use the correct binding class
        ActivitySignUpBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        // Set up click listener for the sign-up button
        binding.continueButton.setOnClickListener(v -> {
            Toast.makeText(SignUpActivity.this, "OTP sent", Toast.LENGTH_SHORT).show();

            // Navigate to Otp_Screen activity
            Intent intent = new Intent(SignUpActivity.this, OtpScreenActivity.class);
            intent.putExtra("flag","2");
            startActivity(intent);
        });
        binding.loginText2.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}
