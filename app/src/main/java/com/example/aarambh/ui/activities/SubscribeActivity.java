package com.example.aarambh.ui.activities;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.example.aarambh.R;
import com.example.aarambh.databinding.ActivitySubscribeBinding;

public class SubscribeActivity extends AppCompatActivity {
    ActivitySubscribeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_subscribe);
        binding.txtDiscount1.setPaintFlags(binding.txtDiscount1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        binding.txtDiscount1.setText("₹499");

        binding.txtDiscount2.setPaintFlags(binding.txtDiscount1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        binding.txtDiscount2.setText("₹1499");

        binding.txtDiscount3.setPaintFlags(binding.txtDiscount1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        binding.txtDiscount3.setText("₹2499");
    }
}