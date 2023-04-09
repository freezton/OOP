package com.example.demo.controllers;

import javafx.scene.control.Alert;

public final class Validator {

    public static boolean isValidId(String inputString) {
        return isValidInteger(inputString) && !MainController.isIdExists(Integer.parseInt(inputString));
    }

    public static boolean isValidNewId(String inputString, int currentId) {
        return isValidInteger(inputString) && MainController.isIdAvailable(Integer.parseInt(inputString), currentId);
    }

    public static boolean isValidRate(String inputString) {
        return isValidInteger(inputString) && Integer.parseInt(inputString) <= 5 && Integer.parseInt(inputString) >= 0;
    }

    public static boolean isValidInteger(String inputString) {
        try {
            Integer.parseInt(inputString);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static void showErrorWindow() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Некорректно введены данные");
        alert.setContentText("Попробуйте еще раз");
        alert.showAndWait();
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
        return isValidDouble(inputString) && inputString.matches("[0-9]+(\\.[0-9]{0,2})?");
    }
}
