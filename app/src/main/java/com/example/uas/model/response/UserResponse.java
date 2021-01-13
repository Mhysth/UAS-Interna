package com.example.uas.model.response;

import com.example.uas.model.local.Timeline;
import com.example.uas.model.local.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
   @SerializedName("")
   private List<User> results;

    public List<User> getResults() {
        return results;
    }
   /*private User results;

    public User getResults() {
        return results;
    }*/
}
