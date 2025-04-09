package com.example.marvelous;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText heroInput;
    private User user;
    private Button rechercher;
    private Button goToFav;
    private ArrayList<Hero> heroes;
    private ArrayList<Hero> fav_heroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        heroInput = findViewById(R.id.heroinput);
        rechercher = findViewById(R.id.searchButton);
        goToFav = findViewById(R.id.favButton);
        user = new User(new ArrayList<>());

        if (getIntent() != null && getIntent().hasExtra("fav_heros")) {
            fav_heroes = getIntent().getParcelableArrayListExtra("fav_heros");
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void goFav(View view) {
        Intent intent = new Intent(this, FavActivity.class);
        intent.putParcelableArrayListExtra("fav_heros", fav_heroes);
        startActivity(intent);
    }


    public void searchHeros(View view) {
        String heroName = heroInput.getText().toString().trim();

        if (heroName.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer un nom de héros", Toast.LENGTH_SHORT).show();
            return;
        }

        String timestamp = MarvelAPICo.getTimestamp();
        String hash = MarvelAPICo.genHash(timestamp);
        String publicKey = MarvelAPICo.getPublicKey();

        MarvelApiInt apiService = MarvelApiClient.getClient().create(MarvelApiInt.class);
        Call<MarvelHeroResponse> call = apiService.searchHeroes(timestamp, publicKey, hash, heroName);

        call.enqueue(new Callback<MarvelHeroResponse>() {
            @Override
            public void onResponse(Call<MarvelHeroResponse> call, Response<MarvelHeroResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ArrayList<Hero> heroes = new ArrayList<>(response.body().getData().getResults());

                    if (heroes.isEmpty()) {
                        showToast("Aucun héros trouvé");
                        return;
                    }

                    Intent intent = new Intent(MainActivity.this, ResActivity.class);
                    intent.putParcelableArrayListExtra("heroes_list", heroes);
                    if (fav_heroes != null) {
                        intent.putParcelableArrayListExtra("fav_heros", fav_heroes);
                    }
                    startActivity(intent);
                } else {
                    showToast("Erreur de réponse du serveur");
                }
            }

            @Override
            public void onFailure(Call<MarvelHeroResponse> call, Throwable t) {
                showToast("Erreur réseau: " + t.getMessage());
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
