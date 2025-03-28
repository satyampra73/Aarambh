package com.example.aarambh.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.aarambh.data.modelclass.usercheck.UserCheckResponse;
import com.example.aarambh.data.repository.AuthRepository;
import com.example.aarambh.ui.utils.CustomProgressBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends ViewModel {

    private final AuthRepository userRepository;
    private final MutableLiveData<UserCheckResponse> userResponse = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private CustomProgressBar progressBar;

    public AuthViewModel() {
        this.userRepository = new AuthRepository();
    }

    public LiveData<UserCheckResponse> getUserResponse() {
        return userResponse;
    }


    public void setProgressBar(CustomProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void checkUser(Context context,String mobile) {
        if (progressBar != null) {
            progressBar.showProgress(context); // Hide progress after response
        }
        userRepository.checkUser(mobile).enqueue(new Callback<UserCheckResponse>() {
            @Override
            public void onResponse(Call<UserCheckResponse> call, Response<UserCheckResponse> response) {
                if (progressBar != null) {
                    progressBar.hideProgress(); // Hide progress after response
                }
                if (response.isSuccessful() && response.body() != null) {
                    userResponse.postValue(response.body());
                } else {
                    errorMessage.postValue("Failed to check user");
                }
            }

            @Override
            public void onFailure(Call<UserCheckResponse> call, Throwable t) {
                if (progressBar != null) {
                    progressBar.hideProgress(); // Hide progress on failure
                }
                errorMessage.postValue(t.getMessage());
            }
        });
    }
}
