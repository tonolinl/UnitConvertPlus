package com.example.helloworld;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;


public class Calculator_Activity extends AppCompatActivity {

    private TextView tvSolution, tvResult;
    private String currentExpression = "";
    private Button btnDeg, btnRad;
    private boolean isDegreeMode = true; // Mode par défaut : degrés

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

        // Restaurer l'état après rotation, si disponible
        if (savedInstanceState != null) {
            currentExpression = savedInstanceState.getString("currentExpression", "");
            tvSolution.setText(savedInstanceState.getString("solutionText", ""));
            tvResult.setText(savedInstanceState.getString("resultText", "0"));

        }

    }

    private void setupButtonListeners() {
        // Liste de tous les boutons, y compris ceux qui doivent être uniquement en mode paysage
        int[] buttonIds = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
                R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9,
                R.id.btn_add, R.id.btn_subtract, R.id.btn_multiply, R.id.btn_divide,
                R.id.btn_dot, R.id.btn_open_parenthesis, R.id.btn_close_parenthesis,
                R.id.btn_clear, R.id.btn_equals, R.id.btn_backspace
        };

        // Ajout des boutons supplémentaires uniquement si le layout est en paysage
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            buttonIds = concatenate(buttonIds, new int[]{
                    R.id.btn_square_root, R.id.btn_cos, R.id.btn_sin,R.id.btn_tan, R.id.btn_log,R.id.btn_ln,
                    R.id.btn_1_over_x, R.id.btn_exponent, R.id.btn_x_squared, R.id.btn_x_power_y,
                    R.id.btn_abs, R.id.btn_pi, R.id.btn_exponent_1,R.id.btn_deg, R.id.btn_rad
            });

            // Initialisation des boutons DEG et RAD uniquement en mode paysage
            btnDeg = findViewById(R.id.btn_deg);
            btnRad = findViewById(R.id.btn_rad);
            if (btnDeg == null) {
                Log.e("ERROR", "btnDeg is null! Check if R.id.btn_deg exists in the layout.");
            } else {
                Log.d("DEBUG", "btnDeg is initialized.");
                setDegreeMode(true);
            }

            if (btnRad == null) {
                Log.e("ERROR", "btnRad is null! Check if R.id.btn_rad exists in the layout.");
            } else {
                Log.d("DEBUG", "btnRad is initialized.");
                setDegreeMode(false);
            }

        }

        // Vérifie chaque bouton et lui ajoute un listener s'il est présent
        for (int id : buttonIds) {
            Button button = findViewById(id);
            if (button != null) {
                button.setOnClickListener(this::onButtonClicked);
            }
        }
        // Initialisation du mode DEG sélectionné par défaut
        if (btnDeg != null) {
            setDegreeMode(true);
        }
    }

    /**
     * Concatène deux tableaux d'entiers.
     */
    private int[] concatenate(int[] first, int[] second) {
        int[] result = new int[first.length + second.length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    public void onDegClicked(View view) {
        Log.d("BUTTON_CLICK", "Mode Degré activé");
        Toast.makeText(this, "Mode Degré activé", Toast.LENGTH_SHORT).show();
    }

    public void onRadClicked(View view) {
        Log.d("BUTTON_CLICK", "Mode Radian activé");
        Toast.makeText(this, "Mode Radian activé", Toast.LENGTH_SHORT).show();
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

    private void setDegreeMode(boolean isDegree) {
        isDegreeMode = isDegree;

        if (isDegreeMode) {
            // Marquer DEG comme sélectionné et RAD comme désélectionné
            btnDeg.setSelected(true);
            btnRad.setSelected(false);

            // Appliquer le style violet pour DEG
            ViewCompat.setBackgroundTintList(btnDeg, ContextCompat.getColorStateList(this, R.color.dark_grey));
            btnDeg.setTextColor(ContextCompat.getColor(this, R.color.white));

            // Restaurer le style par défaut pour RAD
            ViewCompat.setBackgroundTintList(btnRad, ContextCompat.getColorStateList(this, R.color.dark_grey));
            btnRad.setTextColor(ContextCompat.getColor(this, R.color.white));
        } else {
            // Marquer RAD comme sélectionné et DEG comme désélectionné
            btnRad.setSelected(true);
            btnDeg.setSelected(false);

            // Appliquer le style violet pour RAD
            ViewCompat.setBackgroundTintList(btnRad, ContextCompat.getColorStateList(this, R.color.dark_grey));
            btnRad.setTextColor(ContextCompat.getColor(this, R.color.white));

            // Restaurer le style par défaut pour DEG
            ViewCompat.setBackgroundTintList(btnDeg, ContextCompat.getColorStateList(this, R.color.dark_grey));
            btnDeg.setTextColor(ContextCompat.getColor(this, R.color.white));
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
                tvSolution.setText("");
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
                    // Fermer automatiquement les parenthèses ouvertes
                    int openParentheses = currentExpression.length() - currentExpression.replace("(", "").length();
                    int closeParentheses = currentExpression.length() - currentExpression.replace(")", "").length();
                    for (int i = 0; i < (openParentheses - closeParentheses); i++) {
                        currentExpression += ")";
                    }

                    try {


                        double result = evaluateExpression(currentExpression);

                        // Vérifie si le résultat est un entier
                        if (result == (int) result) {
                            tvResult.setText(String.valueOf((int) result)); // Affiche sans ".0"
                        } else {
                            tvResult.setText(String.valueOf(result)); // Affiche normalement
                        }

                        currentExpression = String.valueOf(result);

                    } catch (Exception e) {
                        tvResult.setText("Erreur");
                        currentExpression = "";
                    }
                }
                break;

            case "√":
                // Ajouter "√(" à l'expression actuelle
                currentExpression += "√(";
                tvSolution.setText(currentExpression);
                break;

            case "sin":
                // Ajouter "sin(" à l'expression actuelle
                currentExpression += "sin(";
                tvSolution.setText(currentExpression);
                break;

            case "cos":
                // Ajouter "cos(" à l'expression actuelle
                currentExpression += "cos(";
                tvSolution.setText(currentExpression);
                break;

            case "tan":
                // Ajouter "tan(" à l'expression actuelle
                currentExpression += "tan(";
                tvSolution.setText(currentExpression);
                break;

            case "ln":
                // Ajouter "ln(" à l'expression actuelle
                currentExpression += "ln(";
                tvSolution.setText(currentExpression);
                break;

            case "log":
                // Ajouter "log(" à l'expression actuelle
                currentExpression += "log(";
                tvSolution.setText(currentExpression);
                break;

            case "1/x":
                // Ajouter "1/(" à l'expression actuelle
                currentExpression += "1/(";
                tvSolution.setText(currentExpression);
                break;


            case "x²":
                // Vérifie si l'expression est vide ou se termine par un opérateur
                if (!currentExpression.isEmpty() && Character.isDigit(currentExpression.charAt(currentExpression.length() - 1))) {
                    currentExpression = currentExpression + "^(2)";
                    tvSolution.setText(currentExpression);
                } else {
                    Toast.makeText(this, "Format invalide détecté", Toast.LENGTH_SHORT).show();
                }
                break;

            case "xʸ": // x^y
                // Vérifie si l'expression est vide ou se termine par un opérateur
                if (!currentExpression.isEmpty() && Character.isDigit(currentExpression.charAt(currentExpression.length() - 1))) {
                    // Ajouter "^( à la fin pour permettre à l'utilisateur d'entrer y
                    currentExpression += "^(";
                    tvSolution.setText(currentExpression);
                } else {
                    Toast.makeText(this, "Format invalide détecté", Toast.LENGTH_SHORT).show();
                }
                break;

            case "|x|":
                // Ajouter "abs(" à l'expression actuelle
                currentExpression += "abs(";
                tvSolution.setText(currentExpression);
                break;

            case "π":
                currentExpression += Math.PI;
                tvSolution.setText(currentExpression);
                break;

            case "eˣ":
                currentExpression += "e^("; // Ajoute "e^(" à l'expression
                tvSolution.setText(currentExpression); // Met à jour l'affichage
                break;


            case "e": // Affiche simplement e (la constante d'Euler)
                currentExpression += Math.E; // Ajout de la constante e (environ 2.71828...)
                tvSolution.setText(currentExpression);
                break;

            default: // Gère la saisie des nombres, opérateurs, parenthèses, etc.
                if (isExceedingDecimalLimit(buttonText)) {
                    if (buttonText.equals(".")) {
                        // Si c'est un deuxième point décimal dans le même nombre
                        Toast.makeText(this, "Vous ne pouvez pas saisir deux virgules à la suite", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }

                // Si l'utilisateur essaie de commencer par un opérateur
                if (isOperator(buttonText) && currentExpression.isEmpty()) {
                    Toast.makeText(this, "Format invalide détecté", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Si l'utilisateur essaie d'ajouter un opérateur après un autre opérateur
                if (!currentExpression.isEmpty() && isOperator(buttonText) && isOperator(currentExpression.charAt(currentExpression.length() - 1) + "")) {
                    Toast.makeText(this, "Deux opérateurs consécutifs ne sont pas autorisés", Toast.LENGTH_SHORT).show();
                    return;
                }

                currentExpression += buttonText;
                tvSolution.setText(currentExpression);
                break;
        }
    }
    private boolean isExceedingDecimalLimit(String buttonText) {


        // Diviser l'expression en nombres basés sur les opérateurs mathématiques
        String[] numbers = currentExpression.split("[+\\-×/]");
        if (numbers.length == 0) return false;

        // On récupère uniquement le dernier élément (le nombre en cours d'édition)
        String lastNumber = numbers[numbers.length - 1];

        // Vérifier si l'utilisateur tente d'ajouter un deuxième point décimal dans le même nombre
        if (buttonText.equals(".") && lastNumber.contains(".")) {
            return true; // Deux points dans le même nombre
        }

        return false;
    }

    private boolean isOperator(String text) {
        return text.equals("+") || text.equals("-") || text.equals("×") || text.equals("/") || text.equals("x²")|| text.equals("xʸ") ;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Sauvegarder l'expression actuelle
        outState.putString("currentExpression", currentExpression);

        // Sauvegarder le texte des TextViews (solution et résultat)
        outState.putString("solutionText", tvSolution.getText().toString());
        outState.putString("resultText", tvResult.getText().toString());


    }


    /**
     * Évalue une expression mathématique donnée sous forme de chaîne.
     */
    private double evaluateExpression(String expression) {
        return new ExpressionEvaluator().evaluate(expression);
    }
}
