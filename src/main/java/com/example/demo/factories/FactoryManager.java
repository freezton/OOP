package com.example.demo.factories;

import com.example.demo.classes.*;

public class FactoryManager {

    public AbstractProductFactory getProductFactory(Class<?> productClass) {
        if (productClass.equals(Book.class))
            return new BookFactory();
        else if (productClass.equals(Clothes.class))
            return new ClothesFactory();
        else if (productClass.equals(Electronics.class))
            return new ElectronicsFactory();
        else if (productClass.equals(Food.class))
            return new FoodFactory();
        return null;
    }
}
