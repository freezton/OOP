package com.example.demo.controllers;

import com.example.demo.classes.*;
import com.example.demo.enums.ClothesType;
import com.example.demo.enums.ElectronicsType;
import com.example.demo.enums.Genre;
import com.example.demo.enums.Material;
import com.example.demo.factories.AbstractProductFactory;
import com.example.demo.factories.FactoryManager;
import com.example.demo.factories.ReviewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;

public class MainController implements Initializable {

    @FXML
    private TextArea infoArea;
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
    @FXML
    private TableView<Review> reviewsTableView;
    @FXML
    private TableColumn<Review, String> productReviewColumn;
    @FXML
    private TableColumn<Review, Integer> ratingReviewColumn;
    @FXML
    private TableColumn<Review, String> commentReviewColumn;
    @FXML
    private Button addReviewButton;
    @FXML
    private Button deleteReviewButton;
    @FXML
    private Button editReviewButton;

    private Map<Class<?>, String> titles;
    private Map<Class<?>, String> fxmlNames;
    static ObservableList<Product> items;
    private FactoryManager factoryManager;
    private ObservableList<Review> reviews;
    private ReviewFactory reviewFactory;

    public static boolean isIdExists(int id) {
        for (Product product: items) {
            if (product.getId() == id)
                return true;
        }
        return false;
    }

    private void initClassesComboBox() {
        ObservableList<ProductClass> classes = FXCollections.observableArrayList(
                new ProductClass(Book.class, "Book"),
                new ProductClass(Clothes.class, "Clothes"),
                new ProductClass(Electronics.class, "Electronics"),
                new ProductClass(Food.class, "Food")
        );
        classesComboBox.setItems(classes);
        classesComboBox.setValue(classesComboBox.getItems().get(0));
    }
    private void initProductsTableView() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        items = FXCollections.observableArrayList (
                new Book(1, "Book 1", 16.2, "Just a book", "Author 1", "chel", Genre.FICTION),
                new Book(25, "Book 2", 16.2, "Just a book2", "Author 2", "chel", Genre.DRAMA),
                new Book(14, "Book 3", 16.2, "Just a book3", "Author 3", "chel", Genre.AUTOBIOGRAPHY),
                new Clothes(34, "Cloth 1", 5.55, "Desc", 54, Material.COTTON, ClothesType.BLOUSE),
                new Food(96, "Fud 1", 96.5, "Desc", 30, 600),
                new Electronics(184, "Electronics 1", 5.4, "Desc", ElectronicsType.CAMERA, "Y60"),
                new Book(12, "Book 1", 16.2, "Just a book", "Author 1", "chel", Genre.FICTION),
                new Book(252, "Book 2", 16.2, "Just a book2", "Author 2", "chel", Genre.DRAMA),
                new Book(142, "Book 3", 16.2, "Just a book3", "Author 3", "chel", Genre.AUTOBIOGRAPHY),
                new Clothes(342, "Cloth 1", 5.55, "Desc", 54, Material.COTTON, ClothesType.BLOUSE),
                new Food(926, "Fud 1", 96.5, "Desc", 30, 600),
                new Electronics(183, "Electronics 1", 5.4, "Desc", ElectronicsType.CAMERA, "Y60")
        );
        items.addListener((ListChangeListener<Product>) change -> addReviewButton.setDisable(items.isEmpty()));
        productsTableView.setItems(items);
        productsTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                deleteProductButton.setDisable(false);
                editProductButton.setDisable(false);
                infoArea.setText(newValue.toString());
            } else {
                deleteProductButton.setDisable(true);
                editProductButton.setDisable(true);
                infoArea.setText("No products selected");
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

    private void initReviewTableView() {
        productReviewColumn.setCellValueFactory(new PropertyValueFactory<>("productIdentifier"));
        ratingReviewColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        commentReviewColumn.setCellValueFactory(new PropertyValueFactory<>("text"));

        reviews = FXCollections.observableArrayList(
                new Review(items.get(0), 4, "Cool book!")
        );
        reviewsTableView.setItems(reviews);

        reviewsTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                deleteReviewButton.setDisable(false);
                editReviewButton.setDisable(false);
            } else {
                deleteReviewButton.setDisable(true);
                editReviewButton.setDisable(true);
            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initClassesComboBox();
        initProductsTableView();
        initTitles();
        initFxmlNames();
        initReviewTableView();
        if (!items.isEmpty()) {
            addReviewButton.setDisable(false);
        }
        factoryManager = new FactoryManager();
        reviewFactory = new ReviewFactory();
    }

    @FXML
    void onAddProductButtonClick() {
        Class<?> productClass = classesComboBox.getSelectionModel().getSelectedItem().getProductClass();
        AbstractProductFactory productFactory = factoryManager.getProductFactory(productClass);
        Product product = productFactory.create(fxmlNames.get(productClass), titles.get(productClass));
        if (product != null)
            items.add(product);
    }

    @FXML
    void onEditProductButtonClick() {
        Class<?> productClass = productsTableView.getSelectionModel().getSelectedItem().getClass();
        AbstractProductFactory productFactory = factoryManager.getProductFactory(productClass);
        productFactory.edit(productsTableView.getSelectionModel().getSelectedItem(), fxmlNames.get(productClass), titles.get(productClass));
        productsTableView.refresh();
        reviewsTableView.refresh();
    }

    private List<Review> getReviewsByProduct(Product product) {
        List<Review> list = new ArrayList<>();
        for (Review review: reviews) {
            if (review.getProduct().equals(product)) {
                list.add(review);
            }
        }
        return list;
    }

    @FXML
    void onDeleteProductButtonClick() {
        Product product = productsTableView.getSelectionModel().getSelectedItem();
        for (Review review: getReviewsByProduct(product)) {
            reviews.remove(review);
        }
        items.remove(product);
    }

    @FXML
    void onAddReviewButtonClick() {
        Review review = reviewFactory.create();
        if (review != null) {
            reviews.add(review);
        }
    }

    @FXML
    void onEditReviewButtonClick() {
        reviewFactory.edit(reviewsTableView.getSelectionModel().getSelectedItem());
        reviewsTableView.refresh();
    }
    @FXML
    void onDeleteReviewButtonClick() {
        reviews.remove(reviewsTableView.getSelectionModel().getSelectedItem());
    }
}