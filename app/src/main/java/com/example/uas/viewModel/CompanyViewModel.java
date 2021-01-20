package com.example.uas.viewModel;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.uas.model.local.Company;
import com.example.uas.repository.CompanyRepository;
import java.util.List;
public class CompanyViewModel extends ViewModel {
    private CompanyRepository repository;
    private static final String TAG = "CompanyViewModel";
    public CompanyViewModel() {
    }
    public void init(String token) {
        Log.d(TAG, "init: " + token);
        repository = CompanyRepository.getInstance(token);
    }
    public LiveData<List<Company>> getCompany() {
        return repository.getCompany();
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        repository.resetInstance();
    }
}