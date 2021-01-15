package com.example.uas.model.local;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Company implements Parcelable {

    @SerializedName("id")
    private String id_sv;

    @SerializedName("name")
    private String id_sp;

    @SerializedName("address")
    private String add_sv;

    @SerializedName("supervisor")
    private String sv;

    @SerializedName("email")
    private String email_sv;

    @SerializedName("phone")
    private String phone_sv;

    @SerializedName("npwp")
    private String npwp;

    public Company(String id_sv, String id_sp, String add_sv, String sv, String email_sv, String phone_sv, String npwp) {
        this.id_sv = id_sv;
        this.id_sp = id_sp;
        this.add_sv = add_sv;
        this.sv = sv;
        this.email_sv = email_sv;
        this.phone_sv = phone_sv;
        this.npwp = npwp;
    }

    protected Company(Parcel in) {
        id_sv = in.readString();
        id_sp = in.readString();
        add_sv = in.readString();
        sv = in.readString();
        email_sv = in.readString();
        phone_sv = in.readString();
        npwp = in.readString();
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    public String getId_sv() {
        return id_sv;
    }

    public void setId_sv(String id_sv) {
        this.id_sv = id_sv;
    }

    public String getId_sp() {
        return id_sp;
    }

    public void setId_sp(String id_sp) {
        this.id_sp = id_sp;
    }

    public String getAdd_sv() {
        return add_sv;
    }

    public void setAdd_sv(String add_sv) {
        this.add_sv = add_sv;
    }

    public String getSv() {
        return sv;
    }

    public void setSv(String sv) {
        this.sv = sv;
    }

    public String getEmail_sv() {
        return email_sv;
    }

    public void setEmail_sv(String email_sv) {
        this.email_sv = email_sv;
    }

    public String getPhone_sv() {
        return phone_sv;
    }

    public void setPhone_sv(String phone_sv) {
        this.phone_sv = phone_sv;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id_sv);
        dest.writeString(id_sp);
        dest.writeString(add_sv);
        dest.writeString(sv);
        dest.writeString(email_sv);
        dest.writeString(phone_sv);
        dest.writeString(npwp);
    }
}