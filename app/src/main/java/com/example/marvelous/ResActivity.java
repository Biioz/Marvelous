package com.example.marvelous;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class ResActivity extends AppCompatActivity {

    private ListView heroesListView;
    private HeroAdapter adapter;
    private ArrayList<Hero> heroes = new ArrayList<>(); // Initialisation ici
    private ArrayList<Hero> favHeroes = new ArrayList<>(); // Initialisation ici

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.res_layout);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        // Récupérer les données de l'intent
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("heroes_list")) {
                ArrayList<Hero> receivedHeroes = intent.getParcelableArrayListExtra("heroes_list");
                if (receivedHeroes != null) {
                    heroes.addAll(receivedHeroes); // Maintenant safe car heroes est initialisé
                }
            }
            if (intent.hasExtra("fav_heros")) {
                ArrayList<Hero> receivedFavs = intent.getParcelableArrayListExtra("fav_heros");
                if (receivedFavs != null) {
                    favHeroes.addAll(receivedFavs);
                }
            }
        }

        // Configurer la ListView
        heroesListView = findViewById(R.id.heroes_list_view);
        adapter = new HeroAdapter(this, heroes);
        heroesListView.setAdapter(adapter);

        // Gérer le clic sur un héros
        heroesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Hero selectedHero = heroes.get(position);
                addToFavorites(selectedHero);
            }
        });
    }

    private void addToFavorites(Hero hero) {
        if (!favHeroes.contains(hero)) {
            favHeroes.add(hero);
            Toast.makeText(this, hero.getName() + " ajouté aux favoris", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, hero.getName() + " est déjà dans les favoris", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putParcelableArrayListExtra("fav_heros", favHeroes);
        startActivity(intent);
    }
}