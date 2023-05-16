package com.example.demo.classes;

import com.example.demo.enums.ElectronicsType;

public class Electronics extends Product {
    private ElectronicsType type;
    private String model;

    public Electronics(int id, String name, double price, String description, ElectronicsType type, String model) {
        super(id, name, price, description);
        this.type = type;
        this.model = model;
    }

    private Electronics() {

    }

    public ElectronicsType getType() {
        return type;
    }

    public void setType(ElectronicsType type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Electronics" + "\n" + super.toString() +
                "  type: '" + type + "'\n" +
                "  model: '" + model + "'";
    }
}
