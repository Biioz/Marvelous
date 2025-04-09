package com.example.marvelous;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Objects;
import com.example.marvelous.Hero;

public class User implements Parcelable {

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

    public void addHero(Hero selectedHero) {
        heroList.add(selectedHero);
    }
    protected User(Parcel in) {
        heroList = in.createTypedArrayList(Hero.CREATOR);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(heroList);
    }
}
