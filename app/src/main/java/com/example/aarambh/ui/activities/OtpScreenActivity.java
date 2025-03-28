package com.example.aarambh.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.aarambh.R;
import com.example.aarambh.databinding.ActivityOtpScreenBinding;
import com.example.aarambh.modelclass.OtpResponseModel;
import com.example.aarambh.retrofit.GetData;
import com.example.aarambh.retrofit.RetrofitInstance;

import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpScreenActivity extends AppCompatActivity {

    private ActivityOtpScreenBinding binding;
    private GetData apiService;
    private static final String TAG = "OtpScreenActivity";

    private String flag; // Passed from the Intent
    private String verificationId; // To store the verification ID from API
    private String mobileNumber ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use DataBinding to set content view
        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp_screen);

        mobileNumber = getIntent().getStringExtra("mobile");
        // Initialize Retrofit API service
        apiService = RetrofitInstance.getClient().create(GetData.class);

        // Get Intent extras
        flag = getIntent().getStringExtra("flag");
        if (flag == null) flag = "0"; // Default flag value if not passed

        // Initialize views and listeners
        initializeViews();
        numberOtpMove();

        // Start timer and auto-resend functionality
        startResendOtpTimer();
        resendOtp(); // Trigger OTP sending on screen load
    }

    private void initializeViews() {
        binding.btnVerify.setOnClickListener(v -> handleVerifyClick());
        binding.btnResendOtp.setOnClickListener(v -> handleResendOtpClick());
        binding.btnResendOtp.setVisibility(View.GONE); // Initially hide the button
    }


    private void startResendOtpTimer() {
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String timeRemaining = String.format("You can resend the OTP in %02d:%02d",
                        millisUntilFinished / 1000 / 60, millisUntilFinished / 1000 % 60);
                binding.tvResendTimer.setText(timeRemaining);
            }

            @Override
            public void onFinish() {
                binding.tvResendTimer.setText("You can resend the OTP now.");
                binding.btnResendOtp.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    private void handleVerifyClick() {
        String otp = binding.otp1.getText().toString().trim() +
                binding.otp2.getText().toString().trim() +
                binding.otp3.getText().toString().trim() +
                binding.otp4.getText().toString().trim();

        if (otp.length() != 4) {
            Toast.makeText(OtpScreenActivity.this, "Please enter a valid 4-digit OTP", Toast.LENGTH_SHORT).show();
        } else {
            verifyOtp(otp);
        }
    }

    private void handleResendOtpClick() {
        resendOtp();
        startResendOtpTimer();
    }

    private void resendOtp() {
        String authToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJDLUNCNDhCQTZFQjUzMTQ0MSIsImlhdCI6MTcyMDY5NDY2MCwiZXhwIjoxODc4Mzc0NjYwfQ.xB0K1ZHQeXBVs_qzvaDOTqznBf65cQh2FLD63dF_h7PY3I6URC_oy2LovTYLXMX1R7grqUC0XijQT9_h9NVH9w";
        String countryCode = "91";
        String customerId = "C-8EFA902D5D5A495";

        int otpLength = 4;

        apiService.sendOTP(authToken, countryCode, customerId, "SMS", otpLength, mobileNumber)
                .enqueue(new Callback<OtpResponseModel>() {
                    @Override
                    public void onResponse(Call<OtpResponseModel> call, Response<OtpResponseModel> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            OtpResponseModel responseBody = response.body();
                            if (responseBody.getData() != null) {
                                verificationId = responseBody.getData().getVerificationId();
                                Toast.makeText(OtpScreenActivity.this, "OTP Sent Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.e(TAG, "Verification ID is missing in response");
                                Toast.makeText(OtpScreenActivity.this, "Error: Verification ID missing", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            handleErrorResponse(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<OtpResponseModel> call, Throwable t) {
                        Log.e(TAG, "Error: " + t.getMessage());
                        Toast.makeText(OtpScreenActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void verifyOtp(String otp) {
        if (verificationId == null || verificationId.isEmpty()) {
            Toast.makeText(this, "Verification ID is missing. Please resend OTP.", Toast.LENGTH_SHORT).show();
            return;
        }

        String authToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJDLUNCNDhCQTZFQjUzMTQ0MSIsImlhdCI6MTcyMDY5NDY2MCwiZXhwIjoxODc4Mzc0NjYwfQ.xB0K1ZHQeXBVs_qzvaDOTqznBf65cQh2FLD63dF_h7PY3I6URC_oy2LovTYLXMX1R7grqUC0XijQT9_h9NVH9w";
        String countryCode = "91";
        String mobileNumber = "7355702482";
        String customerId = "C-8EFA902D5D5A495";

        apiService.verifyOTP(authToken, countryCode, mobileNumber, verificationId, customerId, otp)
                .enqueue(new Callback<OtpResponseModel>() {
                    @Override
                    public void onResponse(Call<OtpResponseModel> call, Response<OtpResponseModel> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Toast.makeText(OtpScreenActivity.this, "OTP Verified Successfully", Toast.LENGTH_SHORT).show();
                            navigateToNextScreen();
                        } else {
                            Toast.makeText(OtpScreenActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<OtpResponseModel> call, Throwable t) {
                        Log.e(TAG, "Error: " + t.getMessage());
                        Toast.makeText(OtpScreenActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void navigateToNextScreen() {
        if ("1".equals(flag)) {
            startActivity(new Intent(OtpScreenActivity.this, ChangePassword.class));
        } else {
            startActivity(new Intent(OtpScreenActivity.this, BottomNavigationActivity.class));
        }
        finish();
    }

    private void handleErrorResponse(Response<?> response) {
        try {
            String errorBody = response.errorBody() != null ? response.errorBody().string() : "Unknown Error";
            Log.e(TAG, "Error: " + errorBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Failed to process request. Try again.", Toast.LENGTH_SHORT).show();
    }

    // Method for moving OTP number
    private void numberOtpMove() {
        binding.otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    binding.otp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!s.toString().trim().isEmpty()){
                    binding.otp1.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    binding.otp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!s.toString().trim().isEmpty()){
                    binding.otp2.requestFocus();
                }
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    binding.otp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!s.toString().trim().isEmpty()){
                    binding.otp3.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}
