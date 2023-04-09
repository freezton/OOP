package com.example.demo.controllers;

import com.example.demo.classes.Product;
import com.example.demo.classes.Review;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class ReviewController implements Initializable {

    private Review review;
    @FXML
    private TextArea commentField;
    @FXML
    private ComboBox<Product> productComboBox;
    @FXML
    private TextField ratingField;
    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productComboBox.setItems(MainController.items);
        productComboBox.setValue(MainController.items.get(0));
        productComboBox.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Product> call(ListView<Product> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Product item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getId() + ". " + item.getName());
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });

    }

    private boolean isCorrectForm() {
        return Validator.isValidRate(ratingField.getText());
    }

    @FXML
    void onCancelButtonClick() {
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onConfirmButtonClick() {
        if (isCorrectForm()) {
            if (review == null) {
                review = new Review(
                        productComboBox.getSelectionModel().getSelectedItem(),
                        Integer.parseInt(ratingField.getText()),
                        commentField.getText()
                );
            } else {
                review.setProduct(productComboBox.getSelectionModel().getSelectedItem());
                review.setRating(Integer.parseInt(ratingField.getText()));
                review.setText(commentField.getText());
            }
            Stage stage = (Stage)cancelButton.getScene().getWindow();
            stage.close();
        } else {
            Validator.showErrorWindow();
        }
    }


    public Review getReview() {
        return review;
    }


    public void setReview(Review review) {
        this.review = review;
        productComboBox.setValue(review.getProduct());
        ratingField.setText(String.valueOf(review.getRating()));
        commentField.setText(review.getText());
    }
}
