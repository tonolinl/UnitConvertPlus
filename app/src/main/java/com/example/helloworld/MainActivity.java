package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    private List<String> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        // Initialisation du RecyclerView
        recyclerView = findViewById(R.id.recyclerViewCategories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Liste des catégories
        categories = Arrays.asList("Température", "Poids", "Volume");

        // Création de l'adaptateur
        adapter = new CategoryAdapter(this, categories);
        recyclerView.setAdapter(adapter);

        Button buttonToCalculator = findViewById(R.id.buttonToCalculator);
        buttonToCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer une intention pour naviguer vers la deuxième activité
                Intent intent = new Intent(MainActivity.this, Calculator_Activity.class);
                startActivity(intent);
            }
        });

    }
}
