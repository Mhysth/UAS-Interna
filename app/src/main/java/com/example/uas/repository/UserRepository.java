package com.example.uas.repository;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.example.uas.model.local.User;
import com.example.uas.model.response.UserResponse;
import com.example.uas.network.RetrofitService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class UserRepository {
    private static UserRepository userRepository;
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
    public MutableLiveData<List<User>> getUser() {
        MutableLiveData<List<User>> listUser = new MutableLiveData<>();
        apiService.getUser().enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body().getResults().size());
                        listUser.postValue(response.body().getResults());
                    }
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return listUser;
    }
}