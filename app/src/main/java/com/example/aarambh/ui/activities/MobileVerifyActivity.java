package com.example.aarambh.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aarambh.R;

public class MobileVerifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mobile_verify);
        // Initialize views
        Button continueButton = findViewById(R.id.continueButton);

        // Set an OnClickListener on the "Continue" button
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MobileVerifyActivity.this, OtpScreenActivity.class);
                intent.putExtra("flag","1");
                startActivity(intent);
                finish(); // Close the Login activity (optional)
            }
        });
    }
}