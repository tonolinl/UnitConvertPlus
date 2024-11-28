package com.example.helloworld;

public class Converter {

    public static double convertTemperature(double value, String fromUnit, String toUnit) {
        switch (fromUnit) {
            case "Celsius":
                if (toUnit.equals("Fahrenheit")) return value * 9 / 5 + 32;
                if (toUnit.equals("Kelvin")) return value + 273.15;
                if (toUnit.equals("Rankine")) return (value + 273.15) * 9 / 5;
                break;
            case "Fahrenheit":
                if (toUnit.equals("Celsius")) return (value - 32) * 5 / 9;
                if (toUnit.equals("Kelvin")) return (value - 32) * 5 / 9 + 273.15;
                if (toUnit.equals("Rankine")) return value + 459.67;
                break;
            case "Kelvin":
                if (toUnit.equals("Celsius")) return value - 273.15;
                if (toUnit.equals("Fahrenheit")) return (value - 273.15) * 9 / 5 + 32;
                if (toUnit.equals("Rankine")) return value * 9 / 5;
                break;
            case "Rankine":
                if (toUnit.equals("Celsius")) return (value - 491.67) * 5 / 9;
                if (toUnit.equals("Fahrenheit")) return value - 459.67;
                if (toUnit.equals("Kelvin")) return value * 5 / 9;
                break;
        }
        return value;
    }

    public static double convertWeight(double value, String fromUnit, String toUnit) {
        switch (fromUnit) {
            case "Kilogramme":
                if (toUnit.equals("Livre")) return value * 2.20462;
                if (toUnit.equals("Gramme")) return value * 1000;
                if (toUnit.equals("Once")) return value * 35.274;
                if (toUnit.equals("Tonne")) return value / 1000;
                if (toUnit.equals("Milligramme")) return value * 1_000_000;
                if (toUnit.equals("Stone")) return value * 0.157473;
                break;
            case "Livre":
                if (toUnit.equals("Kilogramme")) return value / 2.20462;
                if (toUnit.equals("Gramme")) return value * 453.592;
                if (toUnit.equals("Once")) return value * 16;
                if (toUnit.equals("Tonne")) return value / 2204.62;
                if (toUnit.equals("Milligramme")) return value * 453_592;
                if (toUnit.equals("Stone")) return value / 14;
                break;
            case "Gramme":
                if (toUnit.equals("Kilogramme")) return value / 1000;
                if (toUnit.equals("Livre")) return value / 453.592;
                if (toUnit.equals("Once")) return value / 28.3495;
                if (toUnit.equals("Tonne")) return value / 1_000_000;
                if (toUnit.equals("Milligramme")) return value * 1000;
                if (toUnit.equals("Stone")) return value / 6_350.29;
                break;
            case "Tonne":
                if (toUnit.equals("Kilogramme")) return value * 1000;
                if (toUnit.equals("Livre")) return value * 2204.62;
                if (toUnit.equals("Once")) return value * 35_274;
                if (toUnit.equals("Gramme")) return value * 1_000_000;
                if (toUnit.equals("Milligramme")) return value * 1_000_000_000;
                if (toUnit.equals("Stone")) return value * 157.473;
                break;
            case "Milligramme":
                if (toUnit.equals("Gramme")) return value / 1000;
                if (toUnit.equals("Kilogramme")) return value / 1_000_000;
                if (toUnit.equals("Livre")) return value / 453_592;
                if (toUnit.equals("Once")) return value / 28_349.5;
                if (toUnit.equals("Tonne")) return value / 1_000_000_000;
                if (toUnit.equals("Stone")) return value / 6_350_293;
                break;
            case "Stone":
                if (toUnit.equals("Kilogramme")) return value / 0.157473;
                if (toUnit.equals("Livre")) return value * 14;
                if (toUnit.equals("Once")) return value * 224;
                if (toUnit.equals("Gramme")) return value * 6350.29;
                if (toUnit.equals("Milligramme")) return value * 6_350_293;
                if (toUnit.equals("Tonne")) return value / 157.473;
                break;
            case "Once":
                if (toUnit.equals("Kilogramme")) return value / 35.274;
                if (toUnit.equals("Livre")) return value / 16;
                if (toUnit.equals("Gramme")) return value * 28.3495;
                if (toUnit.equals("Tonne")) return value / 35_274;
                if (toUnit.equals("Milligramme")) return value * 28_349.5;
                if (toUnit.equals("Stone")) return value / 224;
                break;
        }
        return value;
    }


    public static double convertVolume(double value, String fromUnit, String toUnit) {
        switch (fromUnit) {
            case "Litre":
                if (toUnit.equals("Gallon")) return value * 0.264172;
                if (toUnit.equals("Millilitre")) return value * 1000;
                if (toUnit.equals("Centilitre")) return value * 100;
                if (toUnit.equals("Pinte")) return value * 2.11338;
                break;
            case "Gallon":
                if (toUnit.equals("Litre")) return value / 0.264172;
                if (toUnit.equals("Millilitre")) return value * 3785.41;
                if (toUnit.equals("Centilitre")) return value * 378.541;
                if (toUnit.equals("Pinte")) return value * 8;
                break;
            case "Millilitre":
                if (toUnit.equals("Litre")) return value / 1000;
                if (toUnit.equals("Gallon")) return value / 3785.41;
                if (toUnit.equals("Centilitre")) return value / 10;
                if (toUnit.equals("Pinte")) return value / 568.261;
                break;
            case "Centilitre":
                if (toUnit.equals("Litre")) return value / 100;
                if (toUnit.equals("Millilitre")) return value * 10;
                if (toUnit.equals("Gallon")) return value / 378.541;
                if (toUnit.equals("Pinte")) return value / 56.8261;
                break;
            case "Pinte":
                if (toUnit.equals("Litre")) return value / 2.11338;
                if (toUnit.equals("Gallon")) return value / 8;
                if (toUnit.equals("Millilitre")) return value * 568.261;
                if (toUnit.equals("Centilitre")) return value * 56.8261;
                break;
        }
        return value;
    }


    public static double convertDistance(double value, String fromUnit, String toUnit) {
        switch (fromUnit) {
            case "Mètre":
                if (toUnit.equals("Kilomètre")) return value / 1000;
                if (toUnit.equals("Mile")) return value * 0.000621371;
                if (toUnit.equals("Pied")) return value * 3.28084;
                if (toUnit.equals("Pouce")) return value * 39.3701;
                break;
            case "Kilomètre":
                if (toUnit.equals("Mètre")) return value * 1000;
                if (toUnit.equals("Mile")) return value * 0.621371;
                if (toUnit.equals("Pied")) return value * 3280.84;
                if (toUnit.equals("Pouce")) return value * 39370.1;
                break;
            case "Mile":
                if (toUnit.equals("Mètre")) return value / 0.000621371;
                if (toUnit.equals("Kilomètre")) return value / 0.621371;
                if (toUnit.equals("Pied")) return value * 5280;
                if (toUnit.equals("Pouce")) return value * 63360;
                break;
            case "Pied":
                if (toUnit.equals("Mètre")) return value / 3.28084;
                if (toUnit.equals("Kilomètre")) return value / 3280.84;
                if (toUnit.equals("Mile")) return value / 5280;
                if (toUnit.equals("Pouce")) return value * 12;
                break;
            case "Pouce":
                if (toUnit.equals("Mètre")) return value / 39.3701;
                if (toUnit.equals("Kilomètre")) return value / 39370.1;
                if (toUnit.equals("Mile")) return value / 63360;
                if (toUnit.equals("Pied")) return value / 12;
                break;
        }
        return value;
    }


    public static double convertSpeed(double value, String fromUnit, String toUnit) {
        switch (fromUnit) {
            case "Kilomètre/heure":
                if (toUnit.equals("Mile/heure")) return value * 0.621371;
                if (toUnit.equals("Mètre/seconde")) return value / 3.6;
                if (toUnit.equals("Noeud")) return value / 1.852;
                break;
            case "Mile/heure":
                if (toUnit.equals("Kilomètre/heure")) return value / 0.621371;
                if (toUnit.equals("Mètre/seconde")) return value * 0.44704;
                if (toUnit.equals("Noeud")) return value / 1.15078;
                break;
            case "Mètre/seconde":
                if (toUnit.equals("Kilomètre/heure")) return value * 3.6;
                if (toUnit.equals("Mile/heure")) return value * 2.23694;
                if (toUnit.equals("Noeud")) return value * 1.94384;
                break;
            case "Noeud":
                if (toUnit.equals("Kilomètre/heure")) return value * 1.852;
                if (toUnit.equals("Mile/heure")) return value * 1.15078;
                if (toUnit.equals("Mètre/seconde")) return value / 1.94384;
                break;
        }
        return value;
    }


    public static double convertFrequency(double value, String fromUnit, String toUnit) {
        switch (fromUnit) {
            case "Hertz":
                if (toUnit.equals("Kilohertz")) return value / 1000;
                if (toUnit.equals("Megahertz")) return value / 1_000_000;
                if (toUnit.equals("Gigahertz")) return value / 1_000_000_000;
                break;
            case "Kilohertz":
                if (toUnit.equals("Hertz")) return value * 1000;
                if (toUnit.equals("Megahertz")) return value / 1000;
                if (toUnit.equals("Gigahertz")) return value / 1_000_000;
                break;
            case "Megahertz":
                if (toUnit.equals("Hertz")) return value * 1_000_000;
                if (toUnit.equals("Kilohertz")) return value * 1000;
                if (toUnit.equals("Gigahertz")) return value / 1000;
                break;
            case "Gigahertz":
                if (toUnit.equals("Hertz")) return value * 1_000_000_000;
                if (toUnit.equals("Kilohertz")) return value * 1_000_000;
                if (toUnit.equals("Megahertz")) return value * 1000;
                break;
        }
        return value;
    }


    public static double convertPressure(double value, String fromUnit, String toUnit) {
        switch (fromUnit) {
            case "Pascal":
                if (toUnit.equals("Bar")) return value / 100000;
                if (toUnit.equals("PSI")) return value * 0.000145038;
                if (toUnit.equals("Atmosphère")) return value / 101325;
                break;
            case "Bar":
                if (toUnit.equals("Pascal")) return value * 100000;
                if (toUnit.equals("PSI")) return value * 14.5038;
                if (toUnit.equals("Atmosphère")) return value / 1.01325;
                break;
            case "PSI":
                if (toUnit.equals("Pascal")) return value / 0.000145038;
                if (toUnit.equals("Bar")) return value / 14.5038;
                if (toUnit.equals("Atmosphère")) return value / 14.696;
                break;
            case "Atmosphère":
                if (toUnit.equals("Pascal")) return value * 101325;
                if (toUnit.equals("Bar")) return value * 1.01325;
                if (toUnit.equals("PSI")) return value * 14.696;
                break;
        }
        return value;
    }


    public static double convertDataByte(double value, String fromUnit, String toUnit) {
        switch (fromUnit) {
            case "Octet":
                if (toUnit.equals("Kilooctet")) return value / 1024;
                if (toUnit.equals("Megaoctet")) return value / 1_048_576;
                if (toUnit.equals("Gigaoctet")) return value / 1_073_741_824;
                if (toUnit.equals("Teraoctet")) return value / 1_099_511_627_776L;
                break;
            case "Kilooctet":
                if (toUnit.equals("Octet")) return value * 1024;
                if (toUnit.equals("Megaoctet")) return value / 1024;
                if (toUnit.equals("Gigaoctet")) return value / 1_048_576;
                if (toUnit.equals("Teraoctet")) return value / 1_073_741_824;
                break;
            case "Megaoctet":
                if (toUnit.equals("Octet")) return value * 1_048_576;
                if (toUnit.equals("Kilooctet")) return value * 1024;
                if (toUnit.equals("Gigaoctet")) return value / 1024;
                if (toUnit.equals("Teraoctet")) return value / 1_048_576;
                break;
            case "Gigaoctet":
                if (toUnit.equals("Octet")) return value * 1_073_741_824;
                if (toUnit.equals("Kilooctet")) return value * 1_048_576;
                if (toUnit.equals("Megaoctet")) return value * 1024;
                if (toUnit.equals("Teraoctet")) return value / 1024;
                break;
            case "Teraoctet":
                if (toUnit.equals("Octet")) return value * 1_099_511_627_776L;
                if (toUnit.equals("Kilooctet")) return value * 1_073_741_824;
                if (toUnit.equals("Megaoctet")) return value * 1_048_576;
                if (toUnit.equals("Gigaoctet")) return value * 1024;
                break;
        }
        return value;
    }

}
