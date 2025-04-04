package com.example.marvelous;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import retrofit2.Call;

public interface MarvelApiInt {
    @GET("v1/public/characters") // enddpoint
    Call<MarvelHeroResponse> searchHeroes(
            @Query("ts") String timestamp,
            @Query("apikey") String apiKey,
            @Query("hash") String hash,
            @Query("nameStartsWith") String heroName
    );
}
