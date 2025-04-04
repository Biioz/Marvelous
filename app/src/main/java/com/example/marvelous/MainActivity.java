package com.example.marvelous;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText heroInput;
    private User user;
    private Button rechercher;
    private Button goToFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des vues
        heroInput = findViewById(R.id.heroinput);
        rechercher = findViewById(R.id.searchButton);
        goToFav = findViewById(R.id.favButton);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /*
    public void goFav(View view) {
        if (user == null || user.getHeroList() == null || user.getHeroList().isEmpty()) {
            Toast.makeText(this, "Aucun héros favori", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, FavActivity.class);
        // Conversion explicite en ArrayList<Parcelable>
        ArrayList<Hero> heroList = new ArrayList<>(user.getHeroList());
        intent.putParcelableArrayListExtra("heroes_list", heroList);
        startActivity(intent);
    }
     */

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
                    ArrayList<Hero> heroes = new ArrayList<>(response.body().getData().getResults());
                    for (Hero i : heroes) {
                        System.out.println(i.getName());
                        System.out.println(i.getDescription());
                    }
                    if (!heroes.isEmpty()) {
                        startResActivity(heroes);
                    } else {
                        showToast("Aucun héros trouvé");
                    }
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

    private void startResActivity(ArrayList<Hero> heroes) {
        Intent intent = new Intent(this, ResActivity.class);
        intent.putParcelableArrayListExtra("heroes_list", heroes);
        startActivity(intent);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}