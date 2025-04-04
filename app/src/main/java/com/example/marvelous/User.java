package com.example.marvelous;

import java.util.ArrayList;
import java.util.Objects;
import com.example.marvelous.Hero;

public class User {

    private ArrayList<Hero> heroList;

    public User(ArrayList<Hero> lst){
        heroList=lst;
    }

    public void addFavorite(Hero hero){
        heroList.add(hero);
    }
    public ArrayList<Hero> getHeroList() {
        return heroList;
    }

    public void setHeroList(ArrayList<Hero> heroList) {
        this.heroList = heroList;
    }
}
