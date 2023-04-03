package com.example.demo.classes;

public class Book extends Product {
    String author;
    String publisher;
    Genre genre;

    public Book(int id, String name, double price, String description, String author, String publisher, Genre genre) {
        super(id, name, price, description);
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
    }
}
