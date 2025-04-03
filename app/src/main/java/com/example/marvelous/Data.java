package com.example.marvelous;
import java.util.List ; 

public class Data {
    private int offset;
    private int limit;
    private int total;
    private int count;
    private List<Hero> results; // Liste des comics/personnages

    // Getters
    public List<Hero> getResults() { return results; }
}
