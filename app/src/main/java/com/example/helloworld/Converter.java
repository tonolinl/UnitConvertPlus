package com.example.helloworld;

public class Converter {

    public static double convertTemperature(double value, String fromUnit, String toUnit) {
        switch (fromUnit) {
            case "Celsius":
                if (toUnit.equals("Fahrenheit")) return value * 9 / 5 + 32;
                if (toUnit.equals("Kelvin")) return value + 273.15;
                break;
            case "Fahrenheit":
                if (toUnit.equals("Celsius")) return (value - 32) * 5 / 9;
                if (toUnit.equals("Kelvin")) return (value - 32) * 5 / 9 + 273.15;
                break;
            case "Kelvin":
                if (toUnit.equals("Celsius")) return value - 273.15;
                if (toUnit.equals("Fahrenheit")) return (value - 273.15) * 9 / 5 + 32;
                break;
        }
        return value; // Si les unités sont identiques
    }

    public static double convertWeight(double value, String fromUnit, String toUnit) {
        if (fromUnit.equals("Kilogramme") && toUnit.equals("Livre")) return value * 2.20462;
        if (fromUnit.equals("Livre") && toUnit.equals("Kilogramme")) return value / 2.20462;
        return value;
    }

    public static double convertVolume(double value, String fromUnit, String toUnit) {
        if (fromUnit.equals("Litre") && toUnit.equals("Gallon")) return value * 0.264172;
        if (fromUnit.equals("Gallon") && toUnit.equals("Litre")) return value / 0.264172;
        return value;
    }
}
