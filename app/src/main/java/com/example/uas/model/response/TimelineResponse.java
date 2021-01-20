package com.example.uas.model.response;
import com.example.uas.model.local.Timeline;
import com.google.gson.annotations.SerializedName;
import java.util.List;
public class TimelineResponse {
    //@SerializedName("data")
    private List<Timeline> results;
    public List<Timeline> getResults() {
        return results;
    }
}