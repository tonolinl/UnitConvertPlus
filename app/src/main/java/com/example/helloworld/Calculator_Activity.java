package com.example.helloworld;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Calculator_Activity extends AppCompatActivity {

    private TextView tvDisplay;
    private String currentExpression = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Charger automatiquement le bon fichier XML selon l'orientation
        setContentView(R.layout.activity_calculator);

        // Initialiser les composants
        tvDisplay = findViewById(R.id.tv_display);
        setButtonListeners();
        handleOrientationButtons();
    }

    /**
     * Associe les boutons de la calculatrice à leurs actions.
     */
    private void setButtonListeners() {
        int[] buttonIds = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3,
                R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7,
                R.id.btn_8, R.id.btn_9, R.id.btn_add, R.id.btn_subtract,
                R.id.btn_multiply, R.id.btn_divide, R.id.btn_clear,
                R.id.btn_dot, R.id.btn_equals, R.id.btn_backspace,
                R.id.btn_open_parenthesis, R.id.btn_close_parenthesis
        };

        for (int id : buttonIds) {
            Button button = findViewById(id);
            if (button != null) {
                button.setOnClickListener(this::onButtonClicked);
            }
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
        if (currentOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
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
                tvDisplay.setText("0");
                break;

            case "⌫":
                if (!currentExpression.isEmpty()) {
                    currentExpression = currentExpression.substring(0, currentExpression.length() - 1);
                    tvDisplay.setText(currentExpression.isEmpty() ? "0" : currentExpression);
                }
                break;

            case "=":
                if (!currentExpression.isEmpty()) {
                    try {
                        double result = evaluateExpression(currentExpression);
                        tvDisplay.setText(String.valueOf(result));
                        currentExpression = String.valueOf(result);
                    } catch (Exception e) {
                        tvDisplay.setText("Erreur");
                        currentExpression = "";
                    }
                }
                break;

            case "(":
            case ")":
                currentExpression += buttonText;
                tvDisplay.setText(currentExpression);
                break;

            default: // Nombres et opérateurs
                currentExpression += buttonText;
                tvDisplay.setText(currentExpression);
        }
    }

    /**
     * Évalue une expression mathématique donnée sous forme de chaîne.
     */
    private double evaluateExpression(String expression) {
        return new ExpressionEvaluator().evaluate(expression);
    }
}
