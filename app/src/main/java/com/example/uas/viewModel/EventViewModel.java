package com.example.uas.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.uas.model.local.Event;
import com.example.uas.repository.EventRepository;

import java.util.List;

public class EventViewModel extends ViewModel {

    private EventRepository repository;
    private static final String TAG = "EventViewModel";

    public EventViewModel() {

    }

    public void init(String token) {
        Log.d(TAG, "init: " + token);
        repository = EventRepository.getInstance(token);
    }

    public LiveData<List<Event>> getEvents() {
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
