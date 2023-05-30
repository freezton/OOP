package com.example.demo.serializers;

import com.example.demo.classes.Product;
import com.example.demo.classes.Review;
import com.example.demo.controllers.Validator;
import com.example.demo.serializers.ProductListWrapper;
import com.example.demo.serializers.ReviewListWrapper;
import com.example.demo.serializers.Serializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class JsonSerializer implements Serializer {

    static Product getProductById(List<Product> products, int id) {
        for (Product product: products) {
            if (product.getId() == id)
                return product;
        }
        return null;
    }

    @Override
    public byte[] serialize(List<Product> products, List<Review> reviews) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ProductListWrapper productListWrapper = new ProductListWrapper(products);
            ReviewListWrapper reviewListWrapper = new ReviewListWrapper(reviews);
            String data = objectMapper.writeValueAsString(productListWrapper) + objectMapper.writeValueAsString(reviewListWrapper);
            return data.getBytes();
//            writer.write(objectMapper.writeValueAsString(productListWrapper));
//            writer.write(objectMapper.writeValueAsString(reviewListWrapper));
        } catch (Exception e) {
            Validator.showAlert(Alert.AlertType.ERROR, "File error", "Error while JSON file serialization!", "Check file info");
            return null;
        }
    }

    @Override
    public void deserialize(List<Product> products, List<Review> reviews, byte[] data) {
        //(BufferedReader reader = new BufferedReader(new FileReader(path)))
        try  {
            String json = new String(data);
            products.clear();
            reviews.clear();
            ObjectMapper objectMapper = new ObjectMapper();
//            String json = reader.readLine();
            ProductListWrapper productListWrapper = objectMapper.readValue(json, ProductListWrapper.class);
            ReviewListWrapper reviewListWrapper = objectMapper.readValue(json.substring(json.indexOf("{\"reviews\"")), ReviewListWrapper.class);
            products.addAll(productListWrapper.getProducts());
            reviews.addAll(reviewListWrapper.getReviews());
            for (Review review: reviewListWrapper.getReviews()) {
                review.setProduct(getProductById(products, review.getProduct().getId()));
            }
        } catch (Exception e) {
            Validator.showAlert(Alert.AlertType.ERROR, "File error", "Error while JSON file serialization!", "Check file info");
        }
    }
}
