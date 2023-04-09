package com.example.demo.classes;

public class Review {
    private Product product;
    private int rating;
    private String text;

    public Review(Product product, int rating, String text) {
        this.product = product;
        this.rating = rating;
        this.text = text;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getProductIdentifier() {
        return product.getId() + ". " + product.getName();
    }
}
