package com.example.uas.viewModel;
import android.util.Log;
import androidx.lifecycle.LiveData;
import com.example.uas.model.local.Report;
import com.example.uas.repository.ReportRepository;
import java.util.List;
public class ReportViewModel {
    private ReportRepository repository;
    private static final String TAG = "ReportViewModel";
    public ReportViewModel() {
    }
    public void init(String token) {
        Log.d(TAG, "init: " + token);
        repository = ReportRepository.getInstance(token);
    }
    public LiveData<List<Report>> getReport() {
        return repository.getReport();
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        repository.resetInstance();
    }
}