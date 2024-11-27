package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ConversionActivity extends AppCompatActivity {

    private EditText inputValue;
    private TextView outputValue;
    private RadioGroup baseUnitRadioGroup, targetUnitRadioGroup;
    private Button convertButton;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_list);

        inputValue = findViewById(R.id.inputValue);
        outputValue = findViewById(R.id.outputValue);
        baseUnitRadioGroup = findViewById(R.id.baseUnitRadioGroup);
        targetUnitRadioGroup = findViewById(R.id.targetUnitRadioGroup);
        convertButton = findViewById(R.id.convertButton);

        // Récupérer la catégorie à partir de l'Intent
        category = getIntent().getStringExtra("category");
        TextView categoryTitle = findViewById(R.id.categoryTitle);
        categoryTitle.setText("Catégorie : " + category);

        // Configurer les unités de base et cibles en fonction de la catégorie
        setupUnitsForCategory();

        // Configurer l'action du bouton Convertir
        convertButton.setOnClickListener(this::convertValue);
    }

    // Configurer les RadioGroup en fonction de la catégorie
    private void setupUnitsForCategory() {
        switch (category) {
            case "Température":
                // Unités pour la température
                setupTemperatureUnits();
                break;
            case "Poids":
                // Unités pour le poids
                setupWeightUnits();
                break;
            case "Volume":
                // Unités pour le volume
                setupVolumeUnits();
                break;
        }
    }

    private void setupTemperatureUnits() {
        // Température : Celsius, Fahrenheit, Kelvin
        addRadioButton(baseUnitRadioGroup, "Celsius");
        addRadioButton(baseUnitRadioGroup, "Fahrenheit");
        addRadioButton(baseUnitRadioGroup, "Kelvin");

        addRadioButton(targetUnitRadioGroup, "Celsius");
        addRadioButton(targetUnitRadioGroup, "Fahrenheit");
        addRadioButton(targetUnitRadioGroup, "Kelvin");
    }

    private void setupWeightUnits() {
        // Poids : Kilogramme, Livre
        addRadioButton(baseUnitRadioGroup, "Kilogramme");
        addRadioButton(baseUnitRadioGroup, "Livre");

        addRadioButton(targetUnitRadioGroup, "Kilogramme");
        addRadioButton(targetUnitRadioGroup, "Livre");
    }

    private void setupVolumeUnits() {
        // Volume : Litre, Gallon
        addRadioButton(baseUnitRadioGroup, "Litre");
        addRadioButton(baseUnitRadioGroup, "Gallon");

        addRadioButton(targetUnitRadioGroup, "Litre");
        addRadioButton(targetUnitRadioGroup, "Gallon");
    }

    // Méthode utilitaire pour ajouter un RadioButton à un RadioGroup
    private void addRadioButton(RadioGroup radioGroup, String text) {
        RadioButton radioButton = new RadioButton(this);
        radioButton.setText(text);
        radioGroup.addView(radioButton);
    }

    // Fonction de conversion lorsqu'on appuie sur le bouton "Convertir"
    public void convertValue(View view) {
        String input = inputValue.getText().toString();
        if (!input.isEmpty()) {
            double value = Double.parseDouble(input);

            // Récupérer les unités sélectionnées
            RadioButton selectedBaseUnit = findViewById(baseUnitRadioGroup.getCheckedRadioButtonId());
            RadioButton selectedTargetUnit = findViewById(targetUnitRadioGroup.getCheckedRadioButtonId());

            if (selectedBaseUnit == null || selectedTargetUnit == null) {
                outputValue.setText("Veuillez sélectionner les unités.");
                return;
            }

            String baseUnit = selectedBaseUnit.getText().toString();
            String targetUnit = selectedTargetUnit.getText().toString();

            // Appeler la méthode de conversion en fonction de la catégorie
            double result = 0;
            switch (category) {
                case "Température":
                    result = Converter.convertTemperature(value, baseUnit, targetUnit);
                    break;
                case "Poids":
                    result = Converter.convertWeight(value, baseUnit, targetUnit);
                    break;
                case "Volume":
                    result = Converter.convertVolume(value, baseUnit, targetUnit);
                    break;
            }

            // Afficher le résultat
            outputValue.setText("Résultat : " + result);
        } else {
            outputValue.setText("Veuillez entrer une valeur.");
        }
    }
}
