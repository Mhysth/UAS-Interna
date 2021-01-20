package com.example.uas.network;


import com.example.uas.model.local.Report;
import com.example.uas.model.local.Timeline;
import com.example.uas.model.response.CompanyResponse;
import com.example.uas.model.response.TokenResponse;
import com.example.uas.model.response.UserResponse;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Endpoints {
    @POST("login")
    @FormUrlEncoded
    Call<TokenResponse> login (@Field("email") String email, @Field("password") String password);
    @GET("report")
    Call<List<Report>> getReport();
    @GET("timeline")
    Call<List<Timeline>> getTimeline();
    @GET("user")
    Call<UserResponse> getUser();
    @GET("company")
    Call<CompanyResponse> getCompany();
    @POST("logout")
    Call<JsonObject> logout();
}