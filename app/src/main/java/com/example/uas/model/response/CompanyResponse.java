package com.example.uas.model.response;

import com.example.uas.model.local.Company;
import com.example.uas.model.local.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompanyResponse {

    @SerializedName("data")
    private List<Company> results;

    public List<Company> getResults() {
        return results;
    }
}
