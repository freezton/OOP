package com.example.demo.classes;

public class Electronics extends Product {
    ElectronicsType type;
    String model;

    public Electronics(int id, String name, double price, String description, ElectronicsType type, String model) {
        super(id, name, price, description);
        this.type = type;
        this.model = model;
    }
}
