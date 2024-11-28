package com.example.helloworld;

import java.util.Stack;

public class ExpressionEvaluator {

    public double evaluate(String expression) {
        Stack<Double> values = new Stack<>();
        Stack<String> operators = new Stack<>();
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

            // Si le token est un '-' marquant un nombre négatif
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
                operators.push(String.valueOf(token));
            }

            // Si le token est une parenthèse fermante
            else if (token == ')') {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop(); // Retirer '('
            }

            // Si le token est un opérateur ou une fonction
            else {
                StringBuilder functionBuffer = new StringBuilder();
                while (i < tokens.length && (Character.isLetter(tokens[i]) || tokens[i] == '(')) {
                    functionBuffer.append(tokens[i++]);
                }
                i--; // Revenir au dernier caractère valide

                String operatorOrFunction = functionBuffer.toString();
                if (isFunction(operatorOrFunction)) {
                    operators.push(operatorOrFunction);
                } else {
                    while (!operators.isEmpty() && hasPrecedence(operatorOrFunction, operators.peek())) {
                        values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                    }
                    operators.push(operatorOrFunction);
                }
            }
        }

        // Appliquer les opérateurs restants
        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }

        // Le résultat final est dans la pile de valeurs
        return values.pop();
    }

    private boolean hasPrecedence(String operator1, String operator2) {
        if (operator2.equals("(") || operator2.equals(")")) return false;
        if ((operator1.equals("×") || operator1.equals("/")) && (operator2.equals("+") || operator2.equals("-"))) return false;
        return true;
    }

    private double applyOperator(String operator, double b, double a) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "×":
                return a * b;
            case "/":
                if (b == 0) throw new ArithmeticException("Division par zéro");
                return a / b;
            case "ln":
                if (b <= 0) throw new ArithmeticException("Valeur invalide pour ln");
                return Math.log(b);
            case "log":
                if (b <= 0) throw new ArithmeticException("Valeur invalide pour log");
                return Math.log10(b);
            case "√":
                if (b < 0) throw new ArithmeticException("Valeur négative pour la racine carrée");
                return Math.sqrt(b);
            case "1/x":
                if (b == 0) throw new ArithmeticException("Division par zéro");
                return 1 / b;
            case "e^":
                return Math.exp(b);
            case "^":
                return Math.pow(a, b);
            case "x²":
                return Math.pow(b, 2);
            default:
                throw new UnsupportedOperationException("Opérateur ou fonction inconnue : " + operator);
        }
    }

    private boolean isFunction(String function) {
        return function.equals("ln") || function.equals("log") || function.equals("√") || function.equals("1/x") || function.equals("e^") || function.equals("x²") || function.equals("^");
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '×' || c == '/';
    }

    public String formatExpressionForDisplay(String operator, String currentExpression, String input) {
        switch (operator) {
            case "ln":
                return "ln(" + currentExpression + ")";
            case "log":
                return "log(" + currentExpression + ")";
            case "1/x":
                return "1/(" + currentExpression + ")";
            case "e^":
                return "e^(" + currentExpression + ")";
            case "x²":
                return currentExpression + "^(2)";
            case "^":
                return currentExpression + "^(" + input + ")";
            default:
                return currentExpression;
        }
    }
}
