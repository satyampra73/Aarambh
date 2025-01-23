package com.example.aarambh.retrofit;

import com.example.aarambh.modelclass.OtpResponseModel;

import retrofit2.Call;

public class CallbackResponse {
    private GetData apiService;

    public CallbackResponse() {
        apiService = RetrofitInstance.getClient().create(GetData.class);
    }

    public Call<OtpResponseModel> sendOTP(String authToken, String countryCode, String customerId, String flowType, int otpLength, String mobileNumber) {
        return apiService.sendOTP(authToken, countryCode, customerId, flowType, otpLength, mobileNumber);
    }

    public Call<OtpResponseModel> verifyOTP(String authToken, String countryCode, String mobileNumber, String verificationId, String customerId, String otpCode) {
        return apiService.verifyOTP(authToken, countryCode, mobileNumber, verificationId, customerId, otpCode);
    }
}
