package com.example.uas.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Timeline implements Parcelable {

    @SerializedName("id")
    private String id_std;

    @SerializedName("study_program_id")
    private String id_sp;

    @SerializedName("date")
    private String date;

    @SerializedName("time")
    private String time;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String desc;

    @SerializedName("abbreviation")
    private String abbrv;

    @SerializedName("study_program")
    private String sp;

    public Timeline(String id_std, String id_sp, String date, String time, String title, String desc, String abbrv, String sp) {
        this.id_std = id_std;
        this.id_sp = id_sp;
        this.date = date;
        this.time = time;
        this.title = title;
        this.desc = desc;
        this.abbrv = abbrv;
        this.sp = sp;
    }

    protected Timeline(Parcel in) {
        id_std = in.readString();
        id_sp = in.readString();
        date = in.readString();
        time = in.readString();
        title = in.readString();
        desc = in.readString();
        abbrv = in.readString();
        sp = in.readString();
    }

    public static final Creator<Timeline> CREATOR = new Creator<Timeline>() {
        @Override
        public Timeline createFromParcel(Parcel in) {
            return new Timeline(in);
        }

        @Override
        public Timeline[] newArray(int size) {
            return new Timeline[size];
        }
    };

    public String getId_std() {
        return id_std;
    }

    public void setId_std(String id_std) {
        this.id_std = id_std;
    }

    public String getId_sp() {
        return id_sp;
    }

    public void setId_sp(String id_sp) {
        this.id_sp = id_sp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String date) {
        this.date = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAbbrv() {
        return abbrv;
    }

    public void setAbbrv(String abbrv) {
        this.abbrv = abbrv;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id_std);
        dest.writeString(id_sp);
        dest.writeString(date);
        dest.writeString(title);
        dest.writeString(desc);
        dest.writeString(abbrv);
        dest.writeString(sp);
    }
}