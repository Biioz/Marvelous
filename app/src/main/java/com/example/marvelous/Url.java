package com.example.marvelous;

import android.os.Parcel;
import android.os.Parcelable;

public class Url implements Parcelable {
    private String type;
    private String url;

    protected Url(Parcel in) {
        type = in.readString();
        url = in.readString();
    }

    public static final Creator<Url> CREATOR = new Creator<Url>() {
        @Override
        public Url createFromParcel(Parcel in) {
            return new Url(in);
        }

        @Override
        public Url[] newArray(int size) {
            return new Url[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(url);
    }

    // Getters...
    public String getType() { return type; }
    public String getUrl() { return url; }
}