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

    // Ajout des nouvelles catégories et unités
    private void setupUnitsForCategory() {
        switch (category) {
            case "Température":
                setupTemperatureUnits();
                break;
            case "Poids":
                setupWeightUnits();
                break;
            case "Volume":
                setupVolumeUnits();
                break;
            case "Distance":
                setupDistanceUnits();
                break;
            case "Data Byte":
                setupDataByteUnits();
                break;
            case "Vitesse":
                setupSpeedUnits();
                break;
            case "Fréquence":
                setupFrequencyUnits();
                break;
            case "Pression":
                setupPressureUnits();
                break;
        }
    }

    private void setupTemperatureUnits() {
        // Température : Celsius, Fahrenheit, Kelvin
        addRadioButton(baseUnitRadioGroup, "Celsius");
        addRadioButton(baseUnitRadioGroup, "Fahrenheit");
        addRadioButton(baseUnitRadioGroup, "Kelvin");
        addRadioButton(baseUnitRadioGroup, "Rankine");

        addRadioButton(targetUnitRadioGroup, "Celsius");
        addRadioButton(targetUnitRadioGroup, "Fahrenheit");
        addRadioButton(targetUnitRadioGroup, "Kelvin");
        addRadioButton(baseUnitRadioGroup, "Rankine");
    }

    private void setupWeightUnits() {
        addRadioButton(baseUnitRadioGroup, "Grammes");
        addRadioButton(baseUnitRadioGroup, "Kilogrammes");
        addRadioButton(baseUnitRadioGroup, "Tonnes");
        addRadioButton(baseUnitRadioGroup, "Livres");
        addRadioButton(baseUnitRadioGroup, "Onces");
        addRadioButton(baseUnitRadioGroup, "Stone");

        addRadioButton(targetUnitRadioGroup, "Grammes");
        addRadioButton(targetUnitRadioGroup, "Kilogrammes");
        addRadioButton(targetUnitRadioGroup, "Tonnes");
        addRadioButton(targetUnitRadioGroup, "Livres");
        addRadioButton(targetUnitRadioGroup, "Onces");
        addRadioButton(targetUnitRadioGroup, "Stone");
    }

    private void setupVolumeUnits() {
        // Volume : Litre, Gallon
        addRadioButton(baseUnitRadioGroup, "Litre");
        addRadioButton(baseUnitRadioGroup, "Centilitre");
        addRadioButton(baseUnitRadioGroup, "Millilitre");
        addRadioButton(baseUnitRadioGroup, "Gallon");
        addRadioButton(baseUnitRadioGroup, "Pinte");

        addRadioButton(targetUnitRadioGroup, "Litre");
        addRadioButton(targetUnitRadioGroup, "Centilitre");
        addRadioButton(targetUnitRadioGroup, "Millilitre");
        addRadioButton(targetUnitRadioGroup, "Gallon");
        addRadioButton(targetUnitRadioGroup, "Pinte");
    }

    // Unités pour la distance
    private void setupDistanceUnits() {
        addRadioButton(baseUnitRadioGroup, "Mètre");
        addRadioButton(baseUnitRadioGroup, "Kilomètre");
        addRadioButton(baseUnitRadioGroup, "Mile");
        addRadioButton(baseUnitRadioGroup, "Pied");
        addRadioButton(baseUnitRadioGroup, "Pouce");

        addRadioButton(targetUnitRadioGroup, "Mètre");
        addRadioButton(targetUnitRadioGroup, "Kilomètre");
        addRadioButton(targetUnitRadioGroup, "Mile");
        addRadioButton(targetUnitRadioGroup, "Pied");
        addRadioButton(targetUnitRadioGroup, "Pouce");
    }

    // Unités pour la vitesse
    private void setupSpeedUnits() {
        addRadioButton(baseUnitRadioGroup, "Mètre/seconde");
        addRadioButton(baseUnitRadioGroup, "Kilomètre/heure");
        addRadioButton(baseUnitRadioGroup, "Mile/heure");
        addRadioButton(baseUnitRadioGroup, "Noeuds");

        addRadioButton(targetUnitRadioGroup, "Mètre/seconde");
        addRadioButton(targetUnitRadioGroup, "Kilomètre/heure");
        addRadioButton(targetUnitRadioGroup, "Mile/heure");
        addRadioButton(targetUnitRadioGroup, "Noeuds");
    }

    // Unités pour la fréquence
    private void setupFrequencyUnits() {
        addRadioButton(baseUnitRadioGroup, "Hertz");
        addRadioButton(baseUnitRadioGroup, "Kilohertz");
        addRadioButton(baseUnitRadioGroup, "Megahertz");
        addRadioButton(baseUnitRadioGroup, "Gigahertz");

        addRadioButton(targetUnitRadioGroup, "Hertz");
        addRadioButton(targetUnitRadioGroup, "Kilohertz");
        addRadioButton(targetUnitRadioGroup, "Megahertz");
        addRadioButton(targetUnitRadioGroup, "Gigahertz");
    }

    // Unités pour la pression
    private void setupPressureUnits() {
        addRadioButton(baseUnitRadioGroup, "Pascal");
        addRadioButton(baseUnitRadioGroup, "Bar");
        addRadioButton(baseUnitRadioGroup, "PSI");
        addRadioButton(baseUnitRadioGroup, "Atmosphère");

        addRadioButton(targetUnitRadioGroup, "Pascal");
        addRadioButton(targetUnitRadioGroup, "Bar");
        addRadioButton(targetUnitRadioGroup, "PSI");
        addRadioButton(targetUnitRadioGroup, "Atmosphère");
    }

    // Unités pour les données
    private void setupDataByteUnits() {
        addRadioButton(baseUnitRadioGroup, "Byte");
        addRadioButton(baseUnitRadioGroup, "Kilobyte");
        addRadioButton(baseUnitRadioGroup, "Megabyte");
        addRadioButton(baseUnitRadioGroup, "Gigabyte");
        addRadioButton(baseUnitRadioGroup, "Terabyte");

        addRadioButton(targetUnitRadioGroup, "Byte");
        addRadioButton(targetUnitRadioGroup, "Kilobyte");
        addRadioButton(targetUnitRadioGroup, "Megabyte");
        addRadioButton(targetUnitRadioGroup, "Gigabyte");
        addRadioButton(targetUnitRadioGroup, "Terabyte");
    }

    // Méthode utilitaire pour ajouter un RadioButton à un RadioGroup
    private void addRadioButton(RadioGroup radioGroup, String text) {
        RadioButton radioButton = new RadioButton(this);
        radioButton.setText(text);
        radioGroup.addView(radioButton);
    }

    /// Étendre convertValue pour inclure les nouvelles catégories
    public void convertValue(View view) {
        String input = inputValue.getText().toString();
        if (!input.isEmpty()) {
            double value = Double.parseDouble(input);

            RadioButton selectedBaseUnit = findViewById(baseUnitRadioGroup.getCheckedRadioButtonId());
            RadioButton selectedTargetUnit = findViewById(targetUnitRadioGroup.getCheckedRadioButtonId());

            if (selectedBaseUnit == null || selectedTargetUnit == null) {
                outputValue.setText("Veuillez sélectionner les unités.");
                return;
            }

            String baseUnit = selectedBaseUnit.getText().toString();
            String targetUnit = selectedTargetUnit.getText().toString();

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
                case "Distance":
                    result = Converter.convertDistance(value, baseUnit, targetUnit);
                    break;
                case "Data Byte":
                    result = Converter.convertDataByte(value, baseUnit, targetUnit);
                    break;
                case "Vitesse":
                    result = Converter.convertSpeed(value, baseUnit, targetUnit);
                    break;
                case "Fréquence":
                    result = Converter.convertFrequency(value, baseUnit, targetUnit);
                    break;
                case "Pression":
                    result = Converter.convertPressure(value, baseUnit, targetUnit);
                    break;
            }

            outputValue.setText("Résultat : " + result);
        } else {
            outputValue.setText("Veuillez entrer une valeur.");
        }
    }
}
