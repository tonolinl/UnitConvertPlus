package com.example.helloworld;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConversionListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> conversionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String category = getIntent().getStringExtra("category");  // Récupérer la catégorie

        // Liste d'unités en fonction de la catégorie
        if ("Température".equals(category)) {
            conversionList = Arrays.asList("Celsius", "Fahrenheit", "Kelvin");
        } else if ("Poids".equals(category)) {
            conversionList = Arrays.asList("Kilogrammes", "Grammes", "Livres");
        } else if ("Volume".equals(category)) {
            conversionList = Arrays.asList("Litre", "Millilitre", "Gallon");
        }

        // Initialiser l'adaptateur
        ConversionAdapter adapter = new ConversionAdapter(this, conversionList);
        recyclerView.setAdapter(adapter);
    }
}



