package com.example.demo.classes;

import com.example.demo.factories.AbstractProductFactory;

public class ProductClass {
    Class<?> productClass;
    String productName;
    String fxmlName;
    String title;
    AbstractProductFactory factory;

    public Class<?> getProductClass() {
        return productClass;
    }

    public String getProductName() {
        return productName;
    }

    public String getFxmlName() {
        return fxmlName;
    }

    public String getTitle() {
        return title;
    }

    public AbstractProductFactory getFactory() {
        return factory;
    }

    public ProductClass(Class<?> productClass, String productName, String fxmlName, String title, AbstractProductFactory factory) {
        this.productClass = productClass;
        this.productName = productName;
        this.fxmlName = fxmlName;
        this.title = title;
        this.factory = factory;
    }

    @Override
    public String toString() {
        return this.getProductName();
    }
}
