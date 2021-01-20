package com.example.uas.viewModel;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.uas.model.local.User;
import com.example.uas.repository.UserRepository;
import java.util.List;
public class AccountViewModel extends ViewModel {
    private UserRepository repository;
    private static final String TAG = "AccountViewModel";
    public AccountViewModel() {
    }
    public void init(String token) {
        Log.d(TAG, "init: " + token);
        repository = UserRepository.getInstance(token);
    }
    public LiveData<List<User>> getUser() {
        return repository.getUser();
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        repository.resetInstance();
    }
}