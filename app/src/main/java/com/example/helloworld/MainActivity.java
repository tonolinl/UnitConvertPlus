package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private SearchView searchView;

    // Liste complète des catégories et unités associées
    private final Map<String, List<String>> categoryUnitMap = new HashMap<>();
    private final List<String> allCategories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        // Initialisation du RecyclerView
        recyclerView = findViewById(R.id.recyclerViewCategories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));

        // Configuration initiale des catégories et unités
        setupCategoryUnitMap();

        // Initialisation du RecyclerView
        recyclerView = findViewById(R.id.recyclerViewCategories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Adapter pour afficher les catégories
        categoryAdapter = new CategoryAdapter(this, new ArrayList<>(allCategories));
        recyclerView.setAdapter(categoryAdapter);

        // Initialisation de la SearchView
        searchView = findViewById(R.id.searchBar);
        setupSearchView();

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

    private void setupCategoryUnitMap() {

        // Ajouter les catégories et leurs unités respectives
        categoryUnitMap.put("Température", Arrays.asList("Celsius", "Fahrenheit", "Kelvin", "Rankine"));
        categoryUnitMap.put("Poids", Arrays.asList("Grammes", "Kilogrammes", "Tonnes", "Livres", "Onces", "Stone"));
        categoryUnitMap.put("Volume", Arrays.asList("Litre", "Centilitre", "Millilitre", "Gallon", "Pinte"));
        categoryUnitMap.put("Distance", Arrays.asList("Mètre", "Kilomètre", "Mile", "Pied", "Pouce"));
        categoryUnitMap.put("Data Byte", Arrays.asList("Octet", "Kilooctet", "Megaoctet", "Gigaoctet", "Teraoctet"));
        categoryUnitMap.put("Vitesse", Arrays.asList("Mètre/seconde", "Kilomètre/heure", "Mile/heure", "Noeud"));
        categoryUnitMap.put("Fréquence", Arrays.asList("Hertz", "Kilohertz", "Megahertz", "Gigahertz"));
        categoryUnitMap.put("Pression", Arrays.asList("Pascal", "Bar", "PSI", "Atmosphère"));

        // Extraire toutes les catégories pour affichage initial
        allCategories.addAll(categoryUnitMap.keySet());
    }


    private void setupSearchView() {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    filterCategories(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    filterCategories(newText);
                    return true;
                }
            });
        }

        private void filterCategories(String query){
            List<String> filteredCategories = new ArrayList<>();

            if (query.isEmpty()) {
                // Afficher toutes les catégories si la recherche est vide
                filteredCategories.addAll(allCategories);
            } else {
                // Filtrer par catégorie ou unité
                for (String category : categoryUnitMap.keySet()) {
                    // Vérifie si le nom de la catégorie correspond
                    if (category.toLowerCase().contains(query.toLowerCase())) {
                        filteredCategories.add(category);
                    } else {
                        // Vérifie si une unité correspond
                        List<String> units = categoryUnitMap.get(category);
                        if (units != null) {
                            for (String unit : units) {
                                if (unit.toLowerCase().contains(query.toLowerCase())) {
                                    filteredCategories.add(category);
                                    break; // Évite les doublons
                                }
                            }
                        }
                    }
                }
            }

            // Mettre à jour l'Adapter avec les résultats filtrés
            categoryAdapter.updateCategories(filteredCategories);
        }


}
