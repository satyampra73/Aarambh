package com.example.aarambh.retrofit;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.aarambh.modelclass.OtpResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UtilMethods extends ViewModel {
    private CallbackResponse repository;

    public UtilMethods() {
        repository = new CallbackResponse();
    }

    public void sendOTP(String authToken, String countryCode, String customerId, String flowType, int otpLength, String mobileNumber) {
        repository.sendOTP(authToken, countryCode, customerId, flowType, otpLength, mobileNumber)
                .enqueue(new Callback<OtpResponseModel>() {
                    @Override
                    public void onResponse(Call<OtpResponseModel> call, Response<OtpResponseModel> response) {
                        Log.d("OTP Response", response.message());


                        if (response.isSuccessful()) {
                            // Handle success
                        } else {
                            // Handle failure
                        }
                    }

                    @Override
                    public void onFailure(Call<OtpResponseModel> call, Throwable t) {
                        // Handle error
                    }
                });
    }

    public void verifyOTP(String authToken, String countryCode, String mobileNumber, String verificationId, String customerId, String otpCode) {
        repository.verifyOTP(authToken, countryCode, mobileNumber, verificationId, customerId, otpCode)
                .enqueue(new Callback<OtpResponseModel>() {
                    @Override
                    public void onResponse(Call<OtpResponseModel> call, Response<OtpResponseModel> response) {
                        if (response.isSuccessful()) {
                            // Handle success
                        } else {
                            // Handle failure
                        }
                    }

                    @Override
                    public void onFailure(Call<OtpResponseModel> call, Throwable t) {
                        // Handle error
                    }
                });
    }
}
