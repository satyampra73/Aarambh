package com.example.aarambh.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;

import com.example.aarambh.R;
import com.example.aarambh.databinding.ActivityOtpScreenBinding;

public class OtpScreenActivity extends AppCompatActivity {

    private ActivityOtpScreenBinding binding;
    private EditText otpDigit1, otpDigit2, otpDigit3, otpDigit4;
    private TextView resendOtpButton, resendTimer;
    private AppCompatButton verifyButton;

    private String flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Using Data Binding to set content view
        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp_screen);

        flag =getIntent().getStringExtra("flag");

        // Initialize Views
        initializeViews();

        // Set up OTP input behavior
        setupOtpInputs();

        // Start OTP resend countdown timer
        startResendOtpTimer();

        // Handle Verify button click
        verifyButton.setOnClickListener(v -> handleVerifyClick());

        // Handle Resend OTP button click
        resendOtpButton.setOnClickListener(v -> handleResendOtpClick());
    }

    private void initializeViews() {
        verifyButton = binding.btnVerify;
        resendOtpButton = binding.btnResendOtp; // Reference to the Resend OTP button
        resendTimer = binding.tvResendTimer;   // Reference to the timer TextView
        otpDigit1 = binding.otp1;
        otpDigit2 = binding.otp2;
        otpDigit3 = binding.otp3;
        otpDigit4 = binding.otp4;

        // Initially hide the Resend OTP button
        resendOtpButton.setVisibility(View.GONE);
    }

    private void setupOtpInputs() {
        otpDigit1.addTextChangedListener(new GenericTextWatcher(otpDigit1, otpDigit2, null));
        otpDigit2.addTextChangedListener(new GenericTextWatcher(otpDigit2, otpDigit3, otpDigit1));
        otpDigit3.addTextChangedListener(new GenericTextWatcher(otpDigit3, otpDigit4, otpDigit2));
        otpDigit4.addTextChangedListener(new GenericTextWatcher(otpDigit4, null, otpDigit3));
    }

    private void startResendOtpTimer() {
        resendOtpButton.setVisibility(View.GONE); // Ensure button is hidden initially
        new CountDownTimer(30000, 1000) { // 30 seconds timer
            @Override
            public void onTick(long millisUntilFinished) {
                resendTimer.setText(String.format("You can resend the OTP in %02d:%02d",
                        millisUntilFinished / 1000 / 60, millisUntilFinished / 1000 % 60));
            }

            @Override
            public void onFinish() {
                resendTimer.setText("You can resend the OTP now.");
                resendOtpButton.setVisibility(View.VISIBLE); // Make the button visible
            }
        }.start();
    }

    private void handleVerifyClick() {
        String otp = otpDigit1.getText().toString().trim() +
                otpDigit2.getText().toString().trim() +
                otpDigit3.getText().toString().trim() +
                otpDigit4.getText().toString().trim();

        if (otp.length() != 4) {

            Toast.makeText(OtpScreenActivity.this, "Please enter a valid 4-digit OTP", Toast.LENGTH_SHORT).show();
        } else {
            verifyOtp(otp);

        }
    }

    private void handleResendOtpClick() {
        // Reset the timer and hide the Resend OTP button again
        startResendOtpTimer();
        Toast.makeText(OtpScreenActivity.this, "OTP resent successfully.", Toast.LENGTH_SHORT).show();
    }

    private void verifyOtp(String otp) {
        // Call your API or perform the OTP verification logic here
        Toast.makeText(OtpScreenActivity.this, "OTP Verified: " + otp, Toast.LENGTH_SHORT).show();



        if (flag.equals("1")){
            //MobileVerify
            //send user to Change Password Activity
            Log.d("strData","ForgetPassword");

            Intent intent = new Intent(OtpScreenActivity.this, ChangePassword.class);
            intent.putExtra("flag","1");
            startActivity(intent);
            finish();
        }
        else {
            //SignUp
            Log.d("strData","SignUp");
            // Navigate to the next screen after OTP is verified
            Intent intent = new Intent(OtpScreenActivity.this, BottomNavigationActivity.class);
            startActivity(intent);
            finish();

        }
    }

    private static class GenericTextWatcher implements TextWatcher {
        private final EditText currentView;
        private final EditText nextView;
        private final EditText previousView;

        public GenericTextWatcher(EditText currentView, EditText nextView, EditText previousView) {
            this.currentView = currentView;
            this.nextView = nextView;
            this.previousView = previousView;
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 1 && nextView != null) {
                nextView.requestFocus(); // Move to next input box
            } else if (s.length() == 0 && previousView != null) {
                previousView.requestFocus(); // Move to previous input box
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // No action needed
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // No action needed
        }
    }
}
