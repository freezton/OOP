package com.example.demo.factories;

import com.example.demo.classes.Review;
import com.example.demo.controllers.ControllerManager;
import com.example.demo.controllers.ReviewController;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class ReviewFactory {

    String resourcesRoot = "/com/example/demo/";

    public Review create() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcesRoot + "reviewForm.fxml"));
        Stage stage = new Stage();
        Object controller = ControllerManager.getController("Review", loader, stage);
        stage.showAndWait();
        return ((ReviewController)controller).getReview();
    }

    public void edit(Review review) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcesRoot + "reviewForm.fxml"));
        Stage stage = new Stage();
        Object controller = ControllerManager.getController("Review", loader, stage);
        ((ReviewController)controller).setReview(review);
        stage.showAndWait();
    }
}
