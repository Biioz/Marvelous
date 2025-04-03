package com.example.marvelous;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelApiInt {
    @GET("v1/public/comics")  // → https://gateway.marvel.com/v1/public/comics
    Call<MarvelResponse> getComics(
            @Query("ts") String timestamp,      // → ?ts=1234
            @Query("apikey") String apiKey,     // → &apikey=XYZ
            @Query("hash") String hash          // → &hash=abc123
    );
}
