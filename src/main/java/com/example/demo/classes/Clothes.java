package com.example.demo.classes;

import com.example.demo.enums.ClothesType;
import com.example.demo.enums.Material;

public class Clothes extends Product {
    private int size;
    private Material material;
    private ClothesType type;

    public ClothesType getType() {
        return type;
    }

    public void setType(ClothesType type) {
        this.type = type;
    }

    public Clothes(int id, String name, double price, String description, int size, Material material, ClothesType type) {
        super(id, name, price, description);
        this.size = size;
        this.material = material;
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}