package com.example.demo.classes;

public class Electronics extends Product {
    Brand brand;
    ElectronicsType type;
    String model;

    public Electronics(int id, String name, double price, String description, Brand brand, String model) {
        super(id, name, price, description);
        this.brand = brand;
        this.model = model;
    }
}
