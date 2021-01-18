package com.example.uas.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.uas.model.local.Timeline;
import com.example.uas.network.RetrofitService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimelineRepository {

    private static TimelineRepository timelineRepository;
    private RetrofitService apiService;
    private static final String TAG = "TimelineRepository";

    private TimelineRepository(String token) {
        Log.d(TAG, "TimelineRepository: " + token);
        apiService = RetrofitService.getInstance(token);
    }

    public static TimelineRepository getInstance(String token) {
        if (timelineRepository == null) {
            timelineRepository = new TimelineRepository(token);
        }
        return timelineRepository;
    }

    public synchronized void resetInstance() {
        if (timelineRepository != null) {
            timelineRepository = null;
        }
    }

    /*public MutableLiveData<List<Timeline>> getEvents() {
        MutableLiveData<List<Timeline>> listTimeline = new MutableLiveData<>();

        apiService.getTimeline().enqueue(new Callback<TimelineResponse>() {
            @Override
            public void onResponse(Call<TimelineResponse> call, Response<TimelineResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body().getResults().size());
                        listTimeline.postValue(response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<TimelineResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listTimeline;
    }*/

    //test
    public MutableLiveData<List<Timeline>> getTimeline() {
        MutableLiveData<List<Timeline>> listTimeline = new MutableLiveData<>();

        apiService.getTimeline().enqueue(new Callback<List<Timeline>>() {
            @Override
            public void onResponse(Call<List<Timeline>> call, Response<List<Timeline>> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body().size());
                        listTimeline.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Timeline>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listTimeline;
    }

    public LiveData<String> logout() {
        MutableLiveData<String> message = new MutableLiveData<>();

        apiService.logout().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        try {
                            JSONObject object = new JSONObject(new Gson().toJson(response.body()));
                            String msg = object.getString("message");
                            Log.d(TAG, "onResponse: " + msg);
                            message.postValue(msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
        return message;
    }
}
