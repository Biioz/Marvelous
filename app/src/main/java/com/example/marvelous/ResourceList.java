package com.example.marvelous;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class ResourceList implements Parcelable {
    private int available;
    private String collectionURI;
    private List<ResourceItem> items;

    protected ResourceList(Parcel in) {
        available = in.readInt();
        collectionURI = in.readString();
        items = in.createTypedArrayList(ResourceItem.CREATOR);
    }

    public static final Creator<ResourceList> CREATOR = new Creator<ResourceList>() {
        @Override
        public ResourceList createFromParcel(Parcel in) {
            return new ResourceList(in);
        }

        @Override
        public ResourceList[] newArray(int size) {
            return new ResourceList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(available);
        dest.writeString(collectionURI);
        dest.writeTypedList(items);
    }

    // Getters...
    public int getAvailable() { return available; }
    public String getCollectionURI() { return collectionURI; }
    public List<ResourceItem> getItems() { return items; }
}