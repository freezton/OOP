package com.example.demo.controllers;

import com.example.demo.classes.*;
import com.example.demo.enums.Genre;
import com.example.demo.factories.AbstractProductFactory;
import com.example.demo.factories.FactoryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button deleteProductButton;
    @FXML
    private Button editProductButton;
    @FXML
    private TableView<Product> productsTableView;
    @FXML
    private TableColumn<Product, Integer> idColumn;
    @FXML
    private ComboBox<ProductClass> classesComboBox;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;

    private Map<Class<?>, String> titles;
    private Map<Class<?>, String> fxmlNames;
    private ObservableList<Product> items;
    private ObservableList<ProductClass> classes;

    private void initClassesComboBox() {
        classesComboBox.getItems().addAll(
                new ProductClass(Book.class, "Book"),
                new ProductClass(Clothes.class, "Clothes"),
                new ProductClass(Electronics.class, "Electronics"),
                new ProductClass(Food.class, "Food")
        );
        classesComboBox.setValue(classesComboBox.getItems().get(0));
    }
    private void initProductsTableView() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        items = FXCollections.observableArrayList (
                new Book(1, "Book 1", 16.2, "Just a book", "Author 1", "chel", Genre.FICTION),
                new Book(25, "Book 2", 16.2, "Just a book", "Author 2", "chel", Genre.DRAMA),
                new Book(14, "Book 3", 16.2, "Just a book", "Author 3", "chel", Genre.AUTOBIOGRAPHY)
        );
        productsTableView.setItems(items);
        productsTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Selected item: " + newValue);
                deleteProductButton.setDisable(false);
                editProductButton.setDisable(false);
            } else {
                deleteProductButton.setDisable(true);
                editProductButton.setDisable(true);
            }
        });
    }
    private void initTitles() {
        titles = new HashMap<>();
        titles.put(Book.class, "Book creation");
        titles.put(Clothes.class, "Clothes creation");
        titles.put(Electronics.class, "Electronics creation");
        titles.put(Food.class, "Food creation");
    }
    private void initFxmlNames() {
        fxmlNames = new HashMap<>();
        fxmlNames.put(Book.class, "bookForm.fxml");
        fxmlNames.put(Clothes.class, "clothesForm.fxml");
        fxmlNames.put(Electronics.class, "electronicsForm.fxml");
        fxmlNames.put(Food.class, "foodForm.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initClassesComboBox();
        initProductsTableView();
        initTitles();
        initFxmlNames();
    }

    @FXML
    void onAddProductButtonClick(ActionEvent event) {
        FactoryManager factory = new FactoryManager();
        Class<?> productClass = classesComboBox.getSelectionModel().getSelectedItem().getProductClass();
        System.out.println(fxmlNames.get(productClass) + " " + titles.get(productClass));
        AbstractProductFactory productFactory = factory.getProductFactory(productClass);
        Product product = productFactory.create(fxmlNames.get(productClass), titles.get(productClass));
        if (product != null)
            items.add(product);
//        System.out.println(factory.create("bookForm.fxml", "rrr"));
    }

    @FXML
    void onEditProductButtonClick(ActionEvent event) {

    }

    @FXML
    void onDeleteProductButtonClick(ActionEvent event) {
        Product selectedProduct = productsTableView.getSelectionModel().getSelectedItem();
        items.remove(selectedProduct);
    }
}