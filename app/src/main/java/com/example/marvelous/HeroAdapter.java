package com.example.marvelous;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class HeroAdapter extends ArrayAdapter<Hero> {

    public HeroAdapter(Context context, List<Hero> heroes) {
        super(context, 0, heroes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Hero hero = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.hero_item, parent, false);
        }

        TextView heroName = convertView.findViewById(R.id.hero_name);
        TextView heroDescription = convertView.findViewById(R.id.hero_description);
        ImageView heroImage = convertView.findViewById(R.id.hero_image);

        heroName.setText(hero.getName());

        // Gérer la description (peut être vide dans l'API)
        String description = hero.getDescription();
        if (description == null || description.isEmpty()) {
            heroDescription.setText("Description non disponible");
        } else {
            heroDescription.setText(description);
        }

        // Charger l'image avec Picasso
        String imageUrl = hero.getThumbnail().getPath() + "." + hero.getThumbnail().getExtension();
        Picasso.get().load(imageUrl).into(heroImage);

        return convertView;
    }
}