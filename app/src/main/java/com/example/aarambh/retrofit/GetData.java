package com.example.aarambh.retrofit;

import com.example.aarambh.modelclass.OtpResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface GetData {

    @POST("/verification/v2/verification/send")
    Call<OtpResponseModel> sendOTP(
            @Header("authToken") String authToken,
            @Query("countryCode") String countryCode,
            @Query("customerId") String customerId,
            @Query("flowType") String flowType,
            @Query("otpLength") int otpLength,
            @Query("mobileNumber") String mobileNumber
    );

    @GET("/verification/v2/verification/validateOtp")
    Call<OtpResponseModel> verifyOTP(
            @Header("authToken") String authToken,
            @Query("countryCode") String countryCode,
            @Query("mobileNumber") String mobileNumber,
            @Query("verificationId") String verificationId,
            @Query("customerId") String customerId,
            @Query("code") String otpCode
    );

    @GET("/auth/v1/authentication/token")
    Call<Void> createToken(
            @Query("country") String country,
            @Query("customerId") String customerId,
            @Query("email") String email,
            @Query("key") String key,
            @Query("scope") String scope
    );
}
