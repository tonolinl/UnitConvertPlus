package com.example.helloworld;

import java.util.Stack;

public class ExpressionEvaluator {

    public double evaluate(String expression) {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        char[] tokens = expression.toCharArray();

        for (int i = 0; i < tokens.length; i++) {
            char token = tokens[i];

            // Ignorer les espaces
            if (token == ' ') continue;

            // Si le token est un chiffre ou un point
            if (Character.isDigit(token) || token == '.') {
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
            default: throw new UnsupportedOperationException("Opérateur inconnu : " + operator);
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '×' || c == '/';
    }
}
