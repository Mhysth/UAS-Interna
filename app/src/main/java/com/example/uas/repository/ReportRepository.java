package com.example.uas.repository;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.example.uas.model.local.Report;
import com.example.uas.model.local.User;
import com.example.uas.model.response.ReportResponse;
import com.example.uas.model.response.UserResponse;
import com.example.uas.network.RetrofitService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ReportRepository {
    private static ReportRepository reportRepository;
    private RetrofitService apiService;
    private static final String TAG = "ReportRepository";
    private ReportRepository(String token) {
        Log.d(TAG, "ReportRepository: " + token);
        apiService = RetrofitService.getInstance(token);
    }
    public static ReportRepository getInstance(String token) {
        if (reportRepository == null) {
            reportRepository = new ReportRepository(token);
        }
        return reportRepository;
    }
    public synchronized void resetInstance() {
        if (reportRepository != null) {
            reportRepository= null;
        }
    }
    public MutableLiveData<List<Report>> getReport() {
        MutableLiveData<List<Report>> listReport = new MutableLiveData<>();
        apiService.getReport().enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body().getResults().size());
                        listReport.postValue(response.body().getResults());
                    }
                }
            }
            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return listReport;
    }
}
