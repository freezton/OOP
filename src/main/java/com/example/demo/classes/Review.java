package com.example.demo.classes;

public class Review {
    Product product;
    User user;
    int rating;
    String text;

    public Review(Product product, User user, int rating, String text) {
        this.product = product;
        this.user = user;
        this.rating = rating;
        this.text = text;
    }
}
