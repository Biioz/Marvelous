package com.example.marvelous;

import java.util.ArrayList;
import java.util.Objects;

public class User {

    private ArrayList<Objects> heroList;

    public User(ArrayList<Objects> lst){
        heroList=lst;
    }

    public void addFavorite(Objects hero){
        heroList.add(hero);
    }
    public ArrayList<Objects> getHeroList() {
        return heroList;
    }

    public void setHeroList(ArrayList<Objects> heroList) {
        this.heroList = heroList;
    }
}
