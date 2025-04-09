package com.example.marvelous;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class ResActivity extends AppCompatActivity {

    private Button homebutton;
    private ListView heroesListView;
    private HeroAdapter adapter;
    private ArrayList<Hero> heroes;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.res_layout);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        // Récupérer la liste des héros depuis l'intent
        heroes = getIntent().getParcelableArrayListExtra("heroes_list");
        user = new User(new ArrayList<>()); // Initialiser l'utilisateur

        // Configurer la ListView
        heroesListView = findViewById(R.id.heroes_list_view);
        adapter = new HeroAdapter(this, heroes);
        heroesListView.setAdapter(adapter);

        // Gérer le clic sur un héros
        heroesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Hero selectedHero = heroes.get(position);
                user.addHero(selectedHero);
                Toast.makeText(ResActivity.this, selectedHero.getName() + " ajouté aux favoris", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goToHome(View view) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("hero_list", user.getHeroList()); // Transmettre l'utilisateur avec ses favoris
        startActivity(i);
    }
}