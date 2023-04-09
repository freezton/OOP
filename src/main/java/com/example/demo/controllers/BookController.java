package com.example.demo.controllers;

import com.example.demo.classes.Book;
import com.example.demo.enums.Genre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class BookController extends AbstractProductController {

    private Book book;
    @FXML
    private TextField authorField;

    @FXML
    private TextField publisherField;

    @FXML
    private ComboBox<Genre> genreField;


    public void initialize() {
        ObservableList<Genre> genres = FXCollections.observableArrayList(Genre.values());
        genreField.setItems(genres);
        genreField.setValue(Genre.COMEDY);
    }

    @Override
    void onConfirmButtonClick(ActionEvent event) {
        if (isCorrectForm()) {
            if (descriptionField.getText().isBlank())
                descriptionField.setText("None");
            if (book == null) {
                book = new Book(
                        Integer.parseInt(idField.getText()),
                        nameField.getText(),
                        Double.parseDouble(priceField.getText()),
                        descriptionField.getText(),
                        authorField.getText(),
                        publisherField.getText(),
                        genreField.getValue()
                );
            } else {
                book.setId(Integer.parseInt(idField.getText()));
                book.setName(nameField.getText());
                book.setPrice(Double.parseDouble(priceField.getText()));
                book.setDescription(descriptionField.getText());
                book.setAuthor(authorField.getText());
                book.setPublisher(publisherField.getText());
                book.setGenre(genreField.getValue());
            }
            Stage stage = (Stage)cancelButton.getScene().getWindow();
            stage.close();
        } else {
            //show error form
        }
    }

    public void setBookProperties(Book book) {
        setCommonProperties(book);
        authorField.setText(book.getAuthor());
        publisherField.setText(book.getPublisher());
        genreField.setValue(book.getGenre());
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

}
