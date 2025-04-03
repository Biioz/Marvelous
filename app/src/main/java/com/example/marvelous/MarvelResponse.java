package com.example.marvelous;

public class MarvelResponse {
    private int code;
    private String status;
    private Data data; // Conteneur principal des données

    // Getters
    public Data getData() { return data; }
}