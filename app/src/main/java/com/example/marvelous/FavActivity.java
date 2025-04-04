package com.example.marvelous;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class FavActivity extends AppCompatActivity {

    private Button goHomeButton;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.favorit_layout);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        Intent i = getIntent();
        //user = i.;
        goHomeButton = findViewById(R.id.homeButton);
    }

    public void goHome(View view) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("user", user.getHeroList());
        startActivity(i);
    }


}
