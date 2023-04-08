package com.example.demo.factories;

import com.example.demo.classes.Book;
import com.example.demo.classes.Product;
import com.example.demo.controllers.AbstractProductController;
import com.example.demo.controllers.BookController;
import com.example.demo.controllers.ControllerManager;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class BookFactory implements AbstractProductFactory {

    @Override
    public Book create(String fxmlFilename, String formTitle) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcesRoot + fxmlFilename));
        Stage stage = new Stage();
        Object controller = ControllerManager.getController(formTitle, loader, stage);
        stage.showAndWait();
        return ((BookController)controller).getBook();
    }

    @Override
    public void edit(Product book, String fxmlFilename, String formTitle) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilename));
        Stage stage = new Stage();
        Object controller = ControllerManager.getController(formTitle, loader, stage);
        ((BookController)controller).setBookProperties((Book)book);
        stage.showAndWait();
    }
}
