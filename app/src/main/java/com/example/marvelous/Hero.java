package com.example.marvelous;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class Hero implements Parcelable {
    private int id;
    private String name;
    private String description;
    private String modified;
    private String resourceURI;
    private List<Url> urls;
    private Thumbnail thumbnail;
    private ResourceList comics;
    private ResourceList stories;
    private ResourceList events;
    private ResourceList series;

    protected Hero(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        modified = in.readString();
        resourceURI = in.readString();
        urls = in.createTypedArrayList(Url.CREATOR);
        thumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
        comics = in.readParcelable(ResourceList.class.getClassLoader());
        stories = in.readParcelable(ResourceList.class.getClassLoader());
        events = in.readParcelable(ResourceList.class.getClassLoader());
        series = in.readParcelable(ResourceList.class.getClassLoader());
    }

    public static final Creator<Hero> CREATOR = new Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel in) {
            return new Hero(in);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(modified);
        dest.writeString(resourceURI);
        dest.writeTypedList(urls);
        dest.writeParcelable(thumbnail, flags);
        dest.writeParcelable(comics, flags);
        dest.writeParcelable(stories, flags);
        dest.writeParcelable(events, flags);
        dest.writeParcelable(series, flags);
    }

    // Getters...

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getModified() {
        return modified;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public ResourceList getComics() {
        return comics;
    }

    public ResourceList getStories() {
        return stories;
    }

    public ResourceList getEvents() {
        return events;
    }

    public ResourceList getSeries() {
        return series;
    }

    public String getImageUrl() {
        return thumbnail.getPath() + "." + thumbnail.getExtension();
    }
}
