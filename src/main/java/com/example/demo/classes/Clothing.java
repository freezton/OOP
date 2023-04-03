package com.example.demo.classes;

public class Clothing extends Product {
    int size;
    Material material;
    String color;

    public Clothing(int id, String name, double price, String description, int size, Material material, String color) {
        super(id, name, price, description);
        this.size = size;
        this.material = material;
        this.color = color;
    }
}
