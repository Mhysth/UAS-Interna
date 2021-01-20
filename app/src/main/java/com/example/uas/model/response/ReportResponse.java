package com.example.uas.model.response;
import com.example.uas.model.local.Report;
import com.google.gson.annotations.SerializedName;
import java.util.List;
public class ReportResponse {
    @SerializedName("data")
    private List<Report> results;
    public List<Report> getResults() {
        return results;
    }
}