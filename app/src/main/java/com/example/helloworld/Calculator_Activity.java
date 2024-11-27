package com.example.helloworld;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class Calculator_Activity extends AppCompatActivity {

    private TextView tvSolution, tvResult;
    private String currentExpression = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // Initialisation des TextView
        tvSolution = findViewById(R.id.solution_tv);
        tvResult = findViewById(R.id.result_tv);

        // Configuration des boutons
        setupButtonListeners();
        handleOrientationButtons();
    }

    /**
     * Méthode appelée lorsque la configuration change (orientation, etc.)
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Charger le layout (les deux orientations utilisent activity_calculator mais peuvent différer selon layout-land)
        setContentView(R.layout.activity_calculator);

        // Réinitialiser les références aux vues et listeners
        tvSolution = findViewById(R.id.solution_tv);
        tvResult = findViewById(R.id.result_tv);

        // Vérifie si les vues ont bien été initialisées avant d'ajouter des listeners
        if (tvSolution == null || tvResult == null) {
            throw new RuntimeException("Les vues ne sont pas correctement initialisées dans activity_calculator.xml");
        }

        // Configure les boutons
        setupButtonListeners();
        handleOrientationButtons();
    }


    /**
     * Configure les listeners pour tous les boutons.
     */
    private void setupButtonListeners() {
        int[] buttonIds = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
                R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9,
                R.id.btn_add, R.id.btn_subtract, R.id.btn_multiply, R.id.btn_divide,
                R.id.btn_dot, R.id.btn_open_parenthesis, R.id.btn_close_parenthesis,
                R.id.btn_clear, R.id.btn_equals, R.id.btn_backspace
        };

        // Vérifie chaque bouton
        for (int id : buttonIds) {
            Button button = findViewById(id);
            if (button == null) {
                // Lance une exception ou log si un bouton manque
                throw new RuntimeException("Bouton manquant ou non initialisé dans le layout avec l'ID : " + getResources().getResourceName(id));
            }
            button.setOnClickListener(this::onButtonClicked);
        }
    }

    /**
     * Gère les boutons pour basculer entre les orientations.
     */
    private void handleOrientationButtons() {
        Button btnChangeOrientation;

        // Boutons selon l'orientation actuelle
        btnChangeOrientation = findViewById(R.id.btn_landscape); // Portrait -> Paysage
        if (btnChangeOrientation == null) {
            btnChangeOrientation = findViewById(R.id.btn_portrait); // Paysage -> Portrait
        }

        if (btnChangeOrientation != null) {
            btnChangeOrientation.setOnClickListener(view -> toggleOrientation());
        }
    }

    /**
     * Basculer entre le mode portrait et le mode paysage.
     */
    private void toggleOrientation() {
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    /**
     * Gère les clics sur les boutons de la calculatrice.
     */
    private void onButtonClicked(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "C":
                currentExpression = "";
                tvSolution.setText("0");
                tvResult.setText("0");
                break;

            case "⌫":
                if (!currentExpression.isEmpty()) {
                    currentExpression = currentExpression.substring(0, currentExpression.length() - 1);
                    tvSolution.setText(currentExpression.isEmpty() ? "0" : currentExpression);
                }
                break;

            case "=":
                if (!currentExpression.isEmpty()) {
                    try {
                        double result = evaluateExpression(currentExpression);
                        tvResult.setText(String.valueOf(result));
                        currentExpression = String.valueOf(result);
                    } catch (Exception e) {
                        tvResult.setText("Erreur");
                        currentExpression = "";
                    }
                }
                break;

            default: // Nombres, parenthèses et opérateurs
                currentExpression += buttonText;
                tvSolution.setText(currentExpression);
        }
    }

    /**
     * Évalue une expression mathématique donnée sous forme de chaîne.
     */
    private double evaluateExpression(String expression) {
        return new ExpressionEvaluator().evaluate(expression);
    }
}
