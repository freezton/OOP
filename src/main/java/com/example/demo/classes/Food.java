package com.example.demo.classes;

public class Food extends Product {
    int expirationDate;
    int calories;

    public Food(int id, String name, double price, String description, int expirationDate, int calories) {
        super(id, name, price, description);
        this.expirationDate = expirationDate;
        this.calories = calories;
    }
}
