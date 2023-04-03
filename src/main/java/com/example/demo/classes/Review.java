package com.example.demo.classes;

public class Review {
    Product product;
    int rating;
    String text;

    public Review(Product product, int rating, String text) {
        this.product = product;
        this.rating = rating;
        this.text = text;
    }
}
