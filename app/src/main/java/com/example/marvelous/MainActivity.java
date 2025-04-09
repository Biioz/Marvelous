package com.example.marvelous;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.marvelous.User;

import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText heroInput;
    private String search;
    private User user;
    private Button rechercher;
    private Button goToFav;

    private  ArrayList<Hero> heroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialisation des vues
        heroInput = findViewById(R.id.heroinput);
        rechercher = findViewById(R.id.searchButton);
        goToFav = findViewById(R.id.favButton);
        search = heroInput.getText().toString();
        user = new User(new ArrayList<>());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    public void goFav(View view) {
        if (user == null || user.getHeroList() == null || user.getHeroList().isEmpty()) {
            Toast.makeText(this, "Aucun héros favori", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, FavActivity.class);
        intent.putExtra("user", user.getHeroList());
        startActivity(intent);
    }


    public void searchHeros(View view) {
        String heroName = heroInput.getText().toString().trim();

        if (heroName.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer un nom de héros", Toast.LENGTH_SHORT).show();
            return;
        }

        // Authentification
        String timestamp = MarvelAPICo.getTimestamp();
        String hash = MarvelAPICo.genHash(timestamp);
        String publicKey = MarvelAPICo.getPublicKey();

        // Appel API
        MarvelApiInt apiService = MarvelApiClient.getClient().create(MarvelApiInt.class);
        Call<MarvelHeroResponse> call = apiService.searchHeroes(timestamp, publicKey, hash, heroName);

        call.enqueue(new Callback<MarvelHeroResponse>() {
            @Override
            public void onResponse(Call<MarvelHeroResponse> call, Response<MarvelHeroResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    heroes = new ArrayList<>(response.body().getData().getResults());
                    //for (Hero i : heroes) {
                        //System.out.println(i.getName());
                        //System.out.println(i.getDescription());
                    //}
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

    public void startResActivity(View view) {
        Intent intent = new Intent(this, ResActivity.class);
        searchHeros(view);
        /*if (heroes.isEmpty()) {
            showToast("Aucun héros trouvé");
        }*/
        intent.putParcelableArrayListExtra("heroes_list", heroes);
        startActivity(intent);
    }
}