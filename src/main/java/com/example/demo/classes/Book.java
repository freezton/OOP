package com.example.demo.classes;

import com.example.demo.enums.Genre;

public class Book extends Product {
    private String author;
    private String publisher;
    private Genre genre;

    @Override
    public String toString() {
        return "Book" + "\n" + super.toString() +
                "  author: '" + author + "\'\n" +
                "  publisher: '" + publisher + "\'\n" +
                "  genre: " + genre ;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Book(int id, String name, double price, String description, String author, String publisher, Genre genre) {
        super(id, name, price, description);
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
    }
}
