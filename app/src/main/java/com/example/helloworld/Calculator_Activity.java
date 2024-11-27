package com.example.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Calculator_Activity extends AppCompatActivity {

    private TextView tvDisplay;
    private String currentExpression = "";
    private String currentOperator = "";
    private double result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        tvDisplay = findViewById(R.id.tv_display);

        // Associer les boutons à leurs actions
        setButtonListeners();
    }

    private void setButtonListeners() {
        int[] buttonIds = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3,
                R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7,
                R.id.btn_8, R.id.btn_9, R.id.btn_add, R.id.btn_subtract,
                R.id.btn_multiply, R.id.btn_divide, R.id.btn_clear,
                R.id.btn_dot, R.id.btn_equals, R.id.btn_backspace
        };

        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(this::onButtonClicked);
        }
    }

    private void onButtonClicked(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "C":
                currentExpression = "";
                currentOperator = "";
                result = 0;
                tvDisplay.setText("0");
                break;

            case "⌫":
                if (!currentExpression.isEmpty()) {
                    currentExpression = currentExpression.substring(0, currentExpression.length() - 1);
                    tvDisplay.setText(currentExpression.isEmpty() ? "0" : currentExpression);
                }
                break;

            case "+":
            case "-":
            case "×":
            case "/":
                if (!currentExpression.isEmpty()) {
                    calculate();
                    currentOperator = buttonText;
                    currentExpression = "";
                }
                break;

            case "=":
                if (!currentExpression.isEmpty()) {
                    calculate();
                    currentOperator = "";
                    tvDisplay.setText(String.valueOf(result));
                    currentExpression = String.valueOf(result);
                }
                break;

            case ".":
                if (!currentExpression.contains(".")) {
                    currentExpression += ".";
                    tvDisplay.setText(currentExpression);
                }
                break;

            default: // Chiffres
                currentExpression += buttonText;
                tvDisplay.setText(currentExpression);
        }
    }

    private void calculate() {
        if (currentExpression.isEmpty()) return;

        double value = Double.parseDouble(currentExpression);
        switch (currentOperator) {
            case "+":
                result += value;
                break;
            case "-":
                result -= value;
                break;
            case "×":
                result *= value;
                break;
            case "/":
                if (value != 0) {
                    result /= value;
                } else {
                    tvDisplay.setText("Erreur");
                }
                break;
            default:
                result = value;
        }
    }
}
