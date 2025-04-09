package com.example.marvelous;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class FavActivity extends AppCompatActivity {

    private ListView favListView;
    private HeroAdapter adapter;
    private ArrayList<Hero> favHeroes;
    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.favorit_layout);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        // Initialiser la liste des favoris
        favHeroes = new ArrayList<>();

        // Récupérer les héros favoris depuis l'intent
        Intent intent = getIntent();
        if (getIntent() != null && getIntent().hasExtra("fav_heros")) {
            ArrayList<Hero> receivedFavs = getIntent().getParcelableArrayListExtra("fav_heros");
            if (receivedFavs != null) {
                favHeroes.addAll(receivedFavs);
            }
        }

        // Configurer la ListView
        favListView = findViewById(R.id.fav_list_view);
        emptyView = findViewById(R.id.empty_view);

        // Vérifier si la liste est vide
        if (favHeroes == null || favHeroes.isEmpty()) {
            emptyView.setVisibility(View.VISIBLE);
            favListView.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.GONE);
            favListView.setVisibility(View.VISIBLE);
            adapter = new HeroAdapter(this, favHeroes);
            favListView.setAdapter(adapter);
        }

        // Gérer le clic sur un héros
        favListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Hero hero = favHeroes.get(position);
                favHeroes.remove(position);
                adapter.notifyDataSetChanged();

                if (favHeroes.isEmpty()) {
                    emptyView.setVisibility(View.VISIBLE);
                    favListView.setVisibility(View.GONE);
                }

                Toast.makeText(FavActivity.this, hero.getName() + " retiré des favoris", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putParcelableArrayListExtra("fav_heros", favHeroes);
        startActivity(intent);
    }
}