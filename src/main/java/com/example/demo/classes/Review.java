package com.example.demo.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Review implements Serializable {
//    @JsonProperty("productIdentifier")
    private Product product;
    private int rating;
    private String text;

    public Review(Product product, int rating, String text) {
        this.product = product;
        this.rating = rating;
        this.text = text;
    }

    private Review() {

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

    @JsonIgnore
    public String getProductIdentifier() {
        return product.getId() + ". " + product.getName();
    }

    @Override
    public String toString() {
        return "Review:" +
                "product=" + product.getId() + '\n' +
                ", rating=" + rating + '\n' +
                ", text='" + text + '\'' +
                '}';
    }
}
