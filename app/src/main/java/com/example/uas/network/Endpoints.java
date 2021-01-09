package com.example.uas.network;


import com.example.uas.model.local.Timeline;
import com.example.uas.model.response.EventResponse;
import com.example.uas.model.response.TimelineResponse;
import com.example.uas.model.response.TokenResponse;
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

    /*@GET("interna_timelines")
    Call<EventResponse> getEvents();*/

    /*@GET("timeline")
    Call<TimelineResponse> getTimeline();*/

    //test
    @GET("timeline")
    Call<List<Timeline>> getTimeline();

    @POST("logout")
    Call<JsonObject> logout();
}
