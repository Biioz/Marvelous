package com.example.marvelous;

import android.os.Parcel;
import android.os.Parcelable;

public class ResourceItem implements Parcelable {
    private String resourceURI;
    private String name;

    protected ResourceItem(Parcel in) {
        resourceURI = in.readString();
        name = in.readString();
    }

    public static final Creator<ResourceItem> CREATOR = new Creator<ResourceItem>() {
        @Override
        public ResourceItem createFromParcel(Parcel in) {
            return new ResourceItem(in);
        }

        @Override
        public ResourceItem[] newArray(int size) {
            return new ResourceItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(resourceURI);
        dest.writeString(name);
    }

    // Getters...
    public String getResourceURI() { return resourceURI; }
    public String getName() { return name; }
}