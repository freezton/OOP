package com.example.demo.classes;

abstract public class Product {
    int id;
    String name;
    double price;
    String description;

    public Product(int id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
