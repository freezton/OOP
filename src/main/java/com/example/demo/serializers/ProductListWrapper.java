package com.example.demo.serializers;

import com.example.demo.classes.Product;

import java.util.List;

public class ProductListWrapper {
    private List<Product> products;

    public ProductListWrapper(List<Product> products) {
        this.products = products;
    }

    public ProductListWrapper() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
