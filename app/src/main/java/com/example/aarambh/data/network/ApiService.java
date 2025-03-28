package com.example.aarambh.data.network;

import com.example.aarambh.data.modelclass.usercheck.UserCheckResponse;
import com.example.aarambh.utils.ApiURLS;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @POST(ApiURLS.CheckUser)
    @FormUrlEncoded
    Call<UserCheckResponse> checkUser(
            @Field("mobile_number") String mobile
    );
}
