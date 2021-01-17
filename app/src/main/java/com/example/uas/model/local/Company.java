package com.example.uas.model.local;

import com.google.gson.annotations.SerializedName;

public class Company {
    @SerializedName("id")
    private String id_company;
    @SerializedName("name")
    private String name;
    @SerializedName("address")
    private String address;
    @SerializedName("supervisor")
    private String supervisior;
    @SerializedName("supervisor_phone")
    private String supervisior_contact;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private String phone;
    @SerializedName("npwp")
    private String npwp;

    public Company(String id_company, String name, String address, String supervisior, String supervisior_contact, String email, String phone, String npwp) {
        this.id_company = id_company;
        this.name = name;
        this.address = address;
        this.supervisior = supervisior;
        this.supervisior_contact = supervisior_contact;
        this.email = email;
        this.phone = phone;
        this.npwp = npwp;
    }

    public String getId_company() {
        return id_company;
    }

    public void setId_company(String id_company) {
        this.id_company = id_company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSupervisior() {
        return supervisior;
    }

    public void setSupervisior(String supervisior) {
        this.supervisior = supervisior;
    }

    public String getSupervisior_contact() {
        return supervisior_contact;
    }

    public void setSupervisior_contact(String supervisior_contact) {
        this.supervisior_contact = supervisior_contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }
}