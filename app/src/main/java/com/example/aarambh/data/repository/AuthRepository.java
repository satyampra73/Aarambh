package com.example.aarambh.data.repository;

import com.example.aarambh.data.modelclass.usercheck.UserCheckResponse;
import com.example.aarambh.data.network.ApiService;
import com.example.aarambh.data.network.RetrofitClient;

import retrofit2.Call;

public class AuthRepository {
    private final ApiService apiService;

    public AuthRepository() {
        this.apiService = RetrofitClient.getApiService();
    }

    public Call<UserCheckResponse> checkUser(String mobile) {
        return apiService.checkUser(mobile);
    }
}
