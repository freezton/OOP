package com.example.demo.classes;

public class ProductClass {
    Class<?> productClass;
    String productName;

    public Class<?> getProductClass() {
        return productClass;
    }

    public String getProductName() {
        return productName;
    }

    public ProductClass(Class<?> productClass, String productName) {
        this.productClass = productClass;
        this.productName = productName;
    }

    @Override
    public String toString() {
        return this.getProductName();
    }
}
