package com.example.marvelous;
import java.util.List ;

public class ResourceList {
    private int available;
    private String collectionURI;
    private List<ResourceItem> items;

    public int getAvailable() { return available; }
    public String getCollectionURI() { return collectionURI; }
    public List<ResourceItem> getItems() { return items; }
}

