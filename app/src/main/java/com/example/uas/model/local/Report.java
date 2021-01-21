package com.example.uas.model.local;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
public class Report implements Parcelable {
    @SerializedName("title")
    private String title;
    @SerializedName("status")
    private String stats;
    public Report(String title, String stats) {
        this.title = title;
        this.stats = stats;
    }
    protected Report(Parcel in) {
        title = in.readString();
        stats = in.readString();
    }
    public static final Creator<Report> CREATOR = new Creator<Report>() {
        @Override
        public Report createFromParcel(Parcel in) {
            return new Report(in);
        }
        @Override
        public Report[] newArray(int size) {
            return new Report[size];
        }
    };
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getStats() {
        return stats;
    }
    public void setStats(String stats) {
        this.stats = stats;
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(stats);
    }
}