package com.example.demo.serializers;

import com.example.demo.classes.Product;
import com.example.demo.classes.Review;
import com.example.demo.controllers.Validator;
import com.example.demo.serializers.Serializer;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinarySerializer implements Serializer {

    @Override
    public byte[] serialize(List<Product> products, List<Review> reviews) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(new ArrayList<>(products));
            objectOutputStream.writeObject(new ArrayList<>(reviews));
            return outputStream.toByteArray();
        } catch (IOException e) {
            Validator.showAlert(Alert.AlertType.ERROR, "File error", "Error while binary file serialization!", "Check file info");
            return null;
        }
    }

    @Override
    public void deserialize(List<Product> products, List<Review> reviews, byte[] data) {
//        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path))) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(data))) {
            products.clear();
            reviews.clear();
            products.addAll((ArrayList<Product>)inputStream.readObject());
            reviews.addAll((ArrayList<Review>)inputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            Validator.showAlert(Alert.AlertType.ERROR, "File error", "Error while binary file serialization!", "Check file info");
        }
    }
}
