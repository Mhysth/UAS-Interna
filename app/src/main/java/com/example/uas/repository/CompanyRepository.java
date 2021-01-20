package com.example.uas.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uas.model.local.Company;
import com.example.uas.model.local.Timeline;
import com.example.uas.model.response.CompanyResponse;
import com.example.uas.network.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyRepository {
    private static CompanyRepository companyRepository;
    private RetrofitService apiService;
    private static final String TAG = "CompanyRepository";
    private CompanyRepository(String token) {
        Log.d(TAG, "CompanyRepository: " + token);
        apiService = RetrofitService.getInstance(token);
    }
    public static CompanyRepository getInstance(String token) {
        if (companyRepository == null) {
            companyRepository = new CompanyRepository(token);
        }
        return companyRepository;
    }
    public synchronized void resetInstance() {
        if (companyRepository != null) {
            companyRepository= null;
        }
    }
    public MutableLiveData<List<Company>> getCompany() {
        MutableLiveData<List<Company>> listCompany = new MutableLiveData<>();
        apiService.getCompany().enqueue(new Callback<CompanyResponse>() {
            @Override
            public void onResponse(Call<CompanyResponse> call, Response<CompanyResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body().getResults().size());
                        listCompany.postValue(response.body().getResults());
                    }
                }
            }
            @Override
            public void onFailure(Call<CompanyResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return listCompany;
    }
}