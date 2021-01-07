package com.example.uas.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.uas.model.local.Timeline;
import com.example.uas.model.response.EventResponse;
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

    public MutableLiveData<List<Timeline>> getEvents() {
        MutableLiveData<List<Timeline>> listEvent = new MutableLiveData<>();

        apiService.getEvents().enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body().getResults().size());
                        listEvent.postValue(response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listEvent;
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
