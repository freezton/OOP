package com.example.demo.controllers;

public final class Validator {

    public static boolean isValidInteger(String inputString) {
        try {
            Integer.parseInt(inputString);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isValidDouble(String inputString) {
        try {
            Double.parseDouble(inputString);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isValidPrice(String inputString) {
        return inputString.matches("[0-9]+(\\.[0-9]{0,2})?");
    }
}
