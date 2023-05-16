package com.example.demo.classes;

public class Food extends Product {
    private int expirationDate;
    private int calories;

    public Food(int id, String name, double price, String description, int expirationDate, int calories) {
        super(id, name, price, description);
        this.expirationDate = expirationDate;
        this.calories = calories;
    }

    private Food() {

    }

    public int getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(int expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Food" + "\n" + super.toString() +
                "  calories: " + calories + "\n" +
                "  expiration date: " + expirationDate;
    }
}
