package com.example.marvelous;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import com.bumptech.glide.Glide;


public class ResActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.res_layout);

        // Configuration de la toolbar
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        // Récupération des héros
        ArrayList<Hero> heroes = getIntent().getParcelableArrayListExtra("heroes_list");

        for (Hero i : heroes) {
        System.out.println(i.getName());
        System.out.println(i.getDescription());
        }

        // Affichage simplifié
        if (heroes != null && !heroes.isEmpty()) {
            displaySimpleHeroList(heroes);
        } else {
            Toast.makeText(this, "Aucun héros trouvé", Toast.LENGTH_SHORT).show();
        }
    }

    private void displaySimpleHeroList(ArrayList<Hero> heroes) {
        LinearLayout container = findViewById(R.id.heroesContainer);

        for (Hero hero : heroes) {
            // Création de la vue pour un héros
            View heroView = getLayoutInflater().inflate(R.layout.item_hero_simple, container, false);

            TextView nameView = heroView.findViewById(R.id.hero_name);
            ImageView imageView = heroView.findViewById(R.id.hero_image);

            nameView.setText(hero.getName());

            // Chargement de l'image avec Glide
            Glide.with(this)
                    .load(hero.getImageUrl())
                    .into(imageView);

            container.addView(heroView);
        }
    }
}