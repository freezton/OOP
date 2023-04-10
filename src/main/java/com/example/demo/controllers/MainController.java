package com.example.demo.controllers;

import com.example.demo.classes.*;
import com.example.demo.enums.ClothesType;
import com.example.demo.enums.ElectronicsType;
import com.example.demo.enums.Genre;
import com.example.demo.enums.Material;
import com.example.demo.factories.*;
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
    @FXML
    private TextArea reviewTextArea;

//    private Map<Class<?>, String> titles;
//    private Map<Class<?>, String> fxmlNames;
    static ObservableList<Product> items;
    private ObservableList<Review> reviews;
    private ReviewFactory reviewFactory;

    public static boolean isIdExists(int id) {
        for (Product product: items) {
            if (product.getId() == id)
                return true;
        }
        return false;
    }

    public static boolean isIdAvailable(int newId, int currentId) {
        if (currentId == newId)
            return true;
        for (Product product: items) {
            if (product.getId() == newId)
                return false;
        }
        return true;
    }

    private void initClassesComboBox() {
        ObservableList<ProductClass> classes = FXCollections.observableArrayList(
                new ProductClass(Book.class, "Book", "bookForm.fxml", "Book creation", new BookFactory()),
                new ProductClass(Clothes.class, "Clothes", "clothesForm.fxml", "Clothes creation", new ClothesFactory()),
                new ProductClass(Electronics.class, "Electronics", "electronicsForm.fxml", "Electronics creation", new ElectronicsFactory()),
                new ProductClass(Food.class, "Food", "foodForm.fxml", "Food creation", new FoodFactory())
        );
        classesComboBox.setItems(classes);
        classesComboBox.setValue(classesComboBox.getItems().get(0));
    }
    private void initProductsTableView() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        items = FXCollections.observableArrayList (
                new Book(1, "1984", 16.99, "1984 is a dystopian novella by George Orwell published in 1949, which follows the life " +
                        "of Winston Smith, a low ranking member of 'the Party', who is frustrated by the omnipresent eyes of the party, and its ominous ruler " +
                        "Big Brother. 'Big Brother' controls every aspect of people's lives.", "George Orwell", "Secker & Warburg", Genre.DYSTOPIA),
                new Book(25, "Harry Potter and the Philosopher's Stone", 8.99, "It is a story about Harry Potter, an orphan brought up " +
                        "by his aunt and uncle because his parents were killed when he was a baby. Harry is unloved by his uncle and aunt but everything changes " +
                        "when he is invited to join Hogwarts School of Witchcraft and Wizardry and he finds out he's a wizard.", "J.K. Rowling",
                        "Scholastic Press", Genre.FANTASY),
                new Book(14, "A Clash of Kings", 21.99, "A Clash of Kings depicts the Seven Kingdoms of Westeros in civil war, while the Night's " +
                        "Watch mounts a reconnaissance to investigate the mysterious people known as wildlings. Meanwhile, Daenerys Targaryen continues her plan " +
                        "to conquer the Seven Kingdoms.", "George R. R. Martin", "Bantam Spectra", Genre.AUTOBIOGRAPHY),
                new Clothes(34, "Black T-shirt for man", 10.99, "Short sleeve, crew neck, black color", 50, Material.COTTON, ClothesType.T_SHIRT),
                new Clothes(54, "Wool trousers for woman", 15.99, "Straight fit, gray color, zip and button closure", 36, Material.WOOL, ClothesType.BLOUSE),
                new Clothes(17, "Unisex sweater", 13.99, "Round neckline, light grey, long sleeves, braided front.", 54, Material.WOOL, ClothesType.SWEATER),
                new Food(96, "Grilled Salmon", 19.99, "Grilled salmon fillet with lemon and herbs", 3, 350),
                new Food(33, "Spinach and Feta Omelette", 9.99, "Fluffy omelette with fresh spinach and crumbled feta cheese", 2, 400),
                new Food(82, "Dark Chocolate Bar", 4.99, "Rich dark chocolate bar with 70% cocoa", 180, 500),
                new Electronics(187, "Apple iPhone 14", 899, "The latest iPhone with advanced features, including A15 Bionic chip" +
                        "and improved camera system", ElectronicsType.SMARTPHONE, "iPhone 14"),
                new Electronics(112, "Samsung 65-inch 4K Smart TV", 1299, "High-quality 4K TV with smart capabilities, including " +
                        "built-in streaming apps and voice control", ElectronicsType.TV, "QN65Q80AAFXZA"),
                new Electronics(240, "Lenovo ThinkPad X1 Carbon", 1599, " Lightweight and durable laptop with high-performance " +
                        "features, including 11th Gen Intel Core processors and up to 16GB RAM", ElectronicsType.LAPTOP, "X1 Carbon Gen 9")
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

    private void initReviewTableView() {
        productReviewColumn.setCellValueFactory(new PropertyValueFactory<>("productIdentifier"));
        ratingReviewColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        commentReviewColumn.setCellValueFactory(new PropertyValueFactory<>("text"));

        reviews = FXCollections.observableArrayList(
                new Review(items.get(7), 5, "I recently had the spinach and feta omelette at a local cafe, and it was absolutely " +
                        "delicious! The omelette was perfectly fluffy and cooked to perfection, with just the right amount of spinach and crumbled " +
                        "feta cheese inside. The combination of flavors was amazing, with the savory feta complementing the slightly bitter taste " +
                        "of the spinach. I also appreciated the fact that it was a healthy option, with plenty of protein and vegetables. The omelette " +
                        "was served with a side salad and some whole-grain toast, which made for a satisfying and well-rounded meal. Overall, I highly " +
                        "recommend the spinach and feta omelette to anyone looking for a tasty and nutritious breakfast option."),
                new Review(items.get(4), 2, "I recently purchased a pair of wool women's trousers online and was very disappointed with the " +
                        "quality of the product. The trousers looked and felt cheap, despite the relatively high price. The wool fabric was scratchy and " +
                        "uncomfortable against my skin, and it didn't feel like it would hold up well over time. The fit was also off, with the trousers " +
                        "feeling too tight around the waist and hips, and too loose around the thighs and ankles. To make matters worse, the stitching on " +
                        "the seams was uneven and looked like it would come apart after just a few wears. Overall, I was extremely dissatisfied with these " +
                        "trousers and would not recommend them to anyone. It's a shame, as I had high hopes for a comfortable and stylish pair of wool " +
                        "trousers for the winter season, but these fell far short of my expectations."),
                new Review(items.get(10), 4, "I recently purchased the Samsung 65-inch 4K Smart TV, and overall I'm quite pleased with the product. " +
                        "The picture quality is outstanding, with vivid colors and crisp details that really make movies and TV shows come to life. The smart " +
                        "capabilities are also a nice feature, with built-in streaming apps and voice control that make it easy to find and watch my favorite " +
                        "content. The TV itself is sleek and stylish, with a thin bezel and a modern design that looks great in any living room. However, " +
                        "I did experience some occasional glitches with the smart features, with the TV freezing up or taking a long time to load content. " +
                        "Additionally, the price point is quite high compared to other TVs on the market, which may be a drawback for some buyers. Overall, " +
                        "I would give the Samsung 65-inch 4K Smart TV a rating of 4 out of 5. While it has some minor issues, the picture quality and smart " +
                        "features make it a great choice for anyone looking for a top-of-the-line TV.")
        );
        reviewsTableView.setItems(reviews);

        reviewsTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                deleteReviewButton.setDisable(false);
                editReviewButton.setDisable(false);
                reviewTextArea.setText(newValue.getProduct().getId() + ". " + newValue.getProduct().getName() + "\n" + "Rate: " + newValue.getRating() + "/5\n" +
                        newValue.getText());
            } else {
                deleteReviewButton.setDisable(true);
                editReviewButton.setDisable(true);
                reviewTextArea.setText("No review selected");
            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initClassesComboBox();
        initProductsTableView();
        initReviewTableView();
        if (!items.isEmpty()) {
            addReviewButton.setDisable(false);
        }
        reviewFactory = new ReviewFactory();
    }

    @FXML
    void onAddProductButtonClick() {
        Class<?> productClass = classesComboBox.getSelectionModel().getSelectedItem().getProductClass();
        AbstractProductFactory productFactory = classesComboBox.getSelectionModel().getSelectedItem().getFactory();
        Product product = productFactory.create(classesComboBox.getSelectionModel().getSelectedItem().getFxmlName(), classesComboBox.getSelectionModel().getSelectedItem().getTitle());
        if (product != null)
            items.add(product);
    }

    @FXML
    void onEditProductButtonClick() {
        Class<?> productClass = productsTableView.getSelectionModel().getSelectedItem().getClass();
        AbstractProductFactory productFactory = classesComboBox.getSelectionModel().getSelectedItem().getFactory();
        productFactory.edit(productsTableView.getSelectionModel().getSelectedItem(), classesComboBox.getSelectionModel().getSelectedItem().getFxmlName(), classesComboBox.getSelectionModel().getSelectedItem().getTitle());
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
        Review newValue = reviewsTableView.getSelectionModel().getSelectedItem();
        reviewTextArea.setText(newValue.getProduct().getId() + ". " + newValue.getProduct().getName() + "\n" + "Rate: " + newValue.getRating() + "/5\n" +
                newValue.getText());
    }
    @FXML
    void onDeleteReviewButtonClick() {
        reviews.remove(reviewsTableView.getSelectionModel().getSelectedItem());
    }
}