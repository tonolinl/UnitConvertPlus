package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Durée de l'affichage en millisecondes (par exemple, 3 secondes)
        int SPLASH_SCREEN_TIME = 2000;

        // Passer à l'activité principale après quelques secondes
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Ferme l'activité SplashActivity
        }, SPLASH_SCREEN_TIME);
    }
}
