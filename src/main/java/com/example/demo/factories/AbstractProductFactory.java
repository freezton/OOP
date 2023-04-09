package com.example.demo.factories;

import com.example.demo.classes.Product;

public interface AbstractProductFactory {

    String resourcesRoot = "/com/example/demo/";
    Product create(String fxmlFilename, String formTitle);

    void edit(Product product, String fxmlFilename, String formTitle);
}
