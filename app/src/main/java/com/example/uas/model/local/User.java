package com.example.uas.model.local;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private String id_user;
    @SerializedName("study_program_id")
    private String study_program;
    @SerializedName("period_id")
    private String period_id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("nim")
    private String nim;
    @SerializedName("gender")
    private String gender;
    @SerializedName("line_account")
    private String line_account;
    @SerializedName("phone")
    private String phone;
    @SerializedName("batch")
    private String batch;
    @SerializedName("description")
    private String description;

    public User(){

    }

    public User(String id_user, String study_program, String period_id, String name, String email, String nim, String gender, String line_account, String phone, String batch, String description) {
        this.id_user = id_user;
        this.study_program = study_program;
        this.period_id = period_id;
        this.name = name;
        this.email = email;
        this.nim = nim;
        this.gender = gender;
        this.line_account = line_account;
        this.phone = phone;
        this.batch = batch;
        this.description = description;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getStudy_program() {
        return study_program;
    }

    public void setStudy_program(String study_program) {
        this.study_program = study_program;
    }

    public String getPeriod_id() {
        return period_id;
    }

    public void setPeriod_id(String period_id) {
        this.period_id = period_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLine_account() {
        return line_account;
    }

    public void setLine_account(String line_account) {
        this.line_account = line_account;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
