package com.example.marvelous;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText heroInput;
    private String search;
    private User user;
    private Button rechercher;
    private Button goToFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        heroInput = findViewById(R.id.heroinput);
        rechercher = findViewById(R.id.searchButton);
        goToFav = findViewById(R.id.favButton);
        search = heroInput.getText().toString();

        //i.putExtra("user",user);


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void goFav(View view) {
        Intent i = new Intent(this, FavActivity.class);
        i.putExtra("user", user.getHeroList());
        startActivity(i);
    }


    public void searchHeros(View view) {
        Intent i = new Intent(this, ResActivity.class);
    }
}