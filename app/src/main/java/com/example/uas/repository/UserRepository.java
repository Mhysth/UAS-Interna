package com.example.uas.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uas.model.local.User;
import com.example.uas.network.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private static UserRepository userRepository;
    private static User user;
    private RetrofitService apiService;
    private static final String TAG = "UserRepository";

    private UserRepository(String token) {
        Log.d(TAG, "UserRepository: " + token);
        apiService = RetrofitService.getInstance(token);
    }

    public static UserRepository getInstance(String token) {
        if (userRepository == null) {
            userRepository = new UserRepository(token);
        }
        return userRepository;
    }

    public synchronized void resetInstance() {
        if (userRepository != null) {
            userRepository= null;
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
    public MutableLiveData<List<User>> getUser() {
        MutableLiveData<List<User>> listUser = new MutableLiveData<>();

        apiService.getUser().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body().size());
                        listUser.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listUser;
    }

    //test account
   /*public MutableLiveData<List<User>> getUser() {
        MutableLiveData<List<User>>listUser = new MutableLiveData<>();

        apiService.getUser().enqueue(new Callback <UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body().getResults());
                        listUser.postValue((List<User>) response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listUser;
    }*/




    /*public LiveData<String> logout() {
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
    }*/
}
