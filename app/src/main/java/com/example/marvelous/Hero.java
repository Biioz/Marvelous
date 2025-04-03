package com.example.marvelous;

import java.util.List ;
public class Hero {
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

    // --- Getters ---
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getModified() { return modified; }
    public String getResourceURI() { return resourceURI; }
    public List<Url> getUrls() { return urls; }
    public Thumbnail getThumbnail() { return thumbnail; }
    public ResourceList getComics() { return comics; }
    public ResourceList getStories() { return stories; }
    public ResourceList getEvents() { return events; }
    public ResourceList getSeries() { return series; }

    // --- Méthodes utiles ---
    public String getImageUrl() {
        return thumbnail.getPath() + "." + thumbnail.getExtension();
    }
}