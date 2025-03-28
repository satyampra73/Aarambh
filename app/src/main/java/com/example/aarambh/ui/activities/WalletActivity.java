package com.example.aarambh.ui.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.example.aarambh.R;
import com.example.aarambh.databinding.ActivityWalletBinding;

public class WalletActivity extends AppCompatActivity {
    ActivityWalletBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Use DataBindingUtil to bind the layout with the activity
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wallet);
    }
}
