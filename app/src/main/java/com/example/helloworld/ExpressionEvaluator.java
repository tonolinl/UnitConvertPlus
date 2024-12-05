package com.example.helloworld;


import java.util.Stack;

public class ExpressionEvaluator {

    private static final double TOLERANCE = 1e-15;

    public double evaluate(String expression) {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        char[] tokens = expression.toCharArray();

        for (int i = 0; i < tokens.length; i++) {
            char token = tokens[i];

            // Ignorer les espaces
            if (token == ' ') continue;

            // Si le token est une fonction exponentielle e^(
            if (token == 'e' && i + 2 < tokens.length && tokens[i + 1] == '^' && tokens[i + 2] == '(') {
                int startIndex = i + 3; // Position après "e^("
                int endIndex = findClosingParenthesis(expression, startIndex);
                String innerExpression = expression.substring(startIndex, endIndex);
                double innerValue = evaluate(innerExpression); // Évaluer le contenu entre parenthèses
                values.push(Math.exp(innerValue)); // Calculer e^(innerValue)
                i = endIndex; // Mettre à jour l'indice pour continuer après ')'
            }

            // Si le token est une fonction Math.abs(
            else if (i + 3 < tokens.length && expression.substring(i, i + 4).equals("abs(")) {
                int startIndex = i + 4; // Position après "abs("
                int endIndex = findClosingParenthesis(expression, startIndex);
                String innerExpression = expression.substring(startIndex, endIndex);
                double innerValue = evaluate(innerExpression); // Évaluer le contenu entre parenthèses
                values.push(Math.abs(innerValue)); // Appliquer Math.abs
                i = endIndex; // Mettre à jour l'indice pour continuer après ')'
            }

            // Si le token est une fonction log(
            else if (i + 3 < tokens.length && expression.substring(i, i + 4).equals("log(")) {
                int startIndex = i + 4; // Position après "log("
                int endIndex = findClosingParenthesis(expression, startIndex);
                String innerExpression = expression.substring(startIndex, endIndex);
                double innerValue = evaluate(innerExpression); // Évaluer le contenu entre parenthèses
                values.push(Math.log10(innerValue)); // Appliquer log10 (logarithme en base 10)
                i = endIndex; // Mettre à jour l'indice pour continuer après ')'
            }

            // Si le token est une fonction ln(
            else if (i + 2 < tokens.length && expression.substring(i, i + 3).equals("ln(")) {
                int startIndex = i + 3; // Position après "ln("
                int endIndex = findClosingParenthesis(expression, startIndex);
                String innerExpression = expression.substring(startIndex, endIndex);
                double innerValue = evaluate(innerExpression); // Évaluer le contenu entre parenthèses
                values.push(Math.log(innerValue)); // Appliquer ln (logarithme népérien)
                i = endIndex; // Mettre à jour l'indice pour continuer après ')'
            }

            // Si le token est une fonction √(
            else if (i + 1 < tokens.length && expression.substring(i, i + 2).equals("√(")) {
                int startIndex = i + 2; // Position après "√("
                int endIndex = findClosingParenthesis(expression, startIndex);
                String innerExpression = expression.substring(startIndex, endIndex);
                double innerValue = evaluate(innerExpression); // Évaluer le contenu entre parenthèses
                if (innerValue < 0) {
                    throw new ArithmeticException("La racine carrée d'un nombre négatif n'est pas définie dans les réels");
                }
                values.push(Math.sqrt(innerValue)); // Appliquer √ (racine carrée)
                i = endIndex; // Mettre à jour l'indice pour continuer après ')'
            }

            // Si le token est une fonction sin(
            else if (i + 3 < tokens.length && expression.substring(i, i + 4).equals("sin(")) {
                int startIndex = i + 4; // Position après "sin("
                int endIndex = findClosingParenthesis(expression, startIndex);
                String innerExpression = expression.substring(startIndex, endIndex);
                double innerValue = evaluate(innerExpression); // Évaluer le contenu entre parenthèses
                // Conversion de degrés en radians (si nécessaire)
                double radians = Math.toRadians(innerValue);
                double result = Math.sin(radians);

                // Si la valeur est proche de zéro, la considérer comme zéro
                if (Math.abs(result) < TOLERANCE) {
                    result = 0;
                }

                values.push(result); // Appliquer cos (cosinus en radians)
                i = endIndex; // Mettre à jour l'indice pour continuer après ')'
            }

            // Si le token est une fonction cos(
            else if (i + 3 < tokens.length && expression.substring(i, i + 4).equals("cos(")) {
                int startIndex = i + 4; // Position après "cos("
                int endIndex = findClosingParenthesis(expression, startIndex);
                String innerExpression = expression.substring(startIndex, endIndex);
                double innerValue = evaluate(innerExpression); // Évaluer le contenu entre parenthèses
                // Conversion de degrés en radians (si nécessaire)
                double radians = Math.toRadians(innerValue);
                double result = Math.cos(radians);


                // Si la valeur est proche de zéro, la considérer comme zéro
                if (Math.abs(result) < TOLERANCE) {
                    result = 0;
                }

                values.push(result); // Appliquer cos (cosinus en radians)
                i = endIndex; // Mettre à jour l'indice pour continuer après ')'
            }

            // Si le token est une fonction tan(
            if (i + 3 < tokens.length && expression.substring(i, i + 4).equals("tan(")) {
                int startIndex = i + 4; // Position après "tan("
                int endIndex = findClosingParenthesis(expression, startIndex);
                String innerExpression = expression.substring(startIndex, endIndex);
                double innerValue = evaluate(innerExpression); // Évaluer le contenu entre parenthèses
                // Conversion de degrés en radians (si nécessaire)
                double radians = Math.toRadians(innerValue);
                double result = Math.tan(radians);
                // Si la valeur est trop petite (approche zéro), la considérer comme zéro
                if (Math.abs(result) < TOLERANCE) {
                    result = 0;
                }// Si le résultat est trop proche de 1 ou -1, arrondir
                else if (Math.abs(result - 1) < TOLERANCE) {
                    result = 1;
                } else if (Math.abs(result + 1) < TOLERANCE) {
                    result = -1;
                }

                values.push(result); // Appliquer tan (tangente en radians)
                i = endIndex; // Mettre à jour l'indice pour continuer après ')'
            }

            // Si le token est un chiffre ou un point
            else if (Character.isDigit(token) || token == '.') {
                StringBuilder buffer = new StringBuilder();
                while (i < tokens.length && (Character.isDigit(tokens[i]) || tokens[i] == '.')) {
                    buffer.append(tokens[i++]);
                }
                i--; // Revenir au dernier caractère valide
                values.push(Double.parseDouble(buffer.toString()));
            }

            // Si le token est un '-' et qu'il marque un nombre négatif
            else if (token == '-' && (i == 0 || tokens[i - 1] == '(' || isOperator(tokens[i - 1]))) {
                StringBuilder buffer = new StringBuilder();
                buffer.append(token); // Ajouter le signe négatif
                i++;
                while (i < tokens.length && (Character.isDigit(tokens[i]) || tokens[i] == '.')) {
                    buffer.append(tokens[i++]);
                }
                i--; // Revenir au dernier caractère valide
                values.push(Double.parseDouble(buffer.toString()));
            }

            // Si le token est une parenthèse ouvrante
            else if (token == '(') {
                operators.push(token);
            }

            // Si le token est une parenthèse fermante
            else if (token == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop(); // Retirer '('
            }

            // Si le token est un opérateur
            else if (isOperator(token)) {
                while (!operators.isEmpty() && hasPrecedence(token, operators.peek())) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(token);
            }
        }

        // Appliquer les opérateurs restants
        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }

        // Le résultat final est dans la pile de valeurs
        return values.pop();
    }

    private boolean hasPrecedence(char operator1, char operator2) {
        if (operator2 == '(' || operator2 == ')') return false;

        // Priorité pour les opérateurs
        if (operator1 == '^' && (operator2 == '×' || operator2 == '/' || operator2 == '+' || operator2 == '-')) return false;

        if ((operator1 == '×' || operator1 == '/') && (operator2 == '+' || operator2 == '-')) return false;
        return true;
    }

    private double applyOperator(char operator, double b, double a) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '×': return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("Division par zéro");
                return a / b;
            case '^': return Math.pow(a, b); // Logique pour l'exponentiation
            default: throw new UnsupportedOperationException("Opérateur inconnu : " + operator);
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '×' || c == '/' || c == '^';
    }

    private int findClosingParenthesis(String expression, int startIndex) {
        int count = 1;
        for (int i = startIndex; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') count++;
            else if (expression.charAt(i) == ')') count--;
            if (count == 0) return i;
        }
        throw new IllegalArgumentException("Parenthesis mismatch");
    }
}
