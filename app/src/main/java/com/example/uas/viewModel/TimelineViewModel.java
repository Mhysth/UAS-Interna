package com.example.uas.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.uas.model.local.Timeline;
import com.example.uas.repository.TimelineRepository;

import java.util.List;

public class TimelineViewModel extends ViewModel {

    private TimelineRepository repository;
    private static final String TAG = "TimelineViewModel";

    public TimelineViewModel() {

    }

    public void init(String token) {
        Log.d(TAG, "init: " + token);
        repository = TimelineRepository.getInstance(token);
    }

    public LiveData<List<Timeline>> getEvents() {
        return repository.getEvents();
    }

    public LiveData<String> logout() {
        return repository.logout();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        repository.resetInstance();
    }
}
