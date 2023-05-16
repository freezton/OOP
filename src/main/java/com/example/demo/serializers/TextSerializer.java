package com.example.demo.serializers;

import com.example.demo.classes.Product;
import com.example.demo.classes.ProductClass;
import com.example.demo.classes.Review;
import com.example.demo.controllers.Validator;
import com.example.demo.serializers.Serializer;
import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.demo.controllers.MainController.classes;

public class TextSerializer implements Serializer {

    private void getFields(List<Field> list, Class o) {
        if (!o.getSuperclass().equals(Object.class)) {
            getFields(list, o.getSuperclass());
        }
        Collections.addAll(list, o.getDeclaredFields());
    }

    @Override
    public void serialize(List<Product> products, List<Review> reviews, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write("Products:\n");
            for (Product product: products) {
                List<Field> fields = new ArrayList<>();
                writer.write("\t" + product.getClass().getSimpleName() + ":\n");
                getFields(fields, product.getClass());
                for (Field field: fields) {
                    field.setAccessible(true);
                    writer.write("\t\t" + field.getName() + "=" + field.get(product) + "\n");
                }
            }
            writer.write("Reviews:\n");
            for (Review review: reviews) {
                List<Field> fields = new ArrayList<>();
                writer.write("\t" + review.getClass().getSimpleName() + ":\n");
                getFields(fields, review.getClass());
                for (Field field: fields) {
                    field.setAccessible(true);
                    String data = field.getName().equals("product") ? String.valueOf(review.getProduct().getId()) : field.get(review).toString();
                    writer.write("\t\t" + field.getName() + "=" + data + "\n");
                }
            }
        } catch (Exception e) {
            Validator.showAlert(Alert.AlertType.ERROR, "File error", "Error while text file serialization!", "Check file info");
        }
    }

    @Override
    public void deserialize(List<Product> products, List<Review> reviews, String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            products.clear();
            reviews.clear();
            String line = reader.readLine();
            while (!(line = reader.readLine().trim()).equals("Reviews:")) {
                Class<? extends Product> productClass = (Class<? extends Product>)getClassByName(classes ,line.substring(0, line.length()-1));
                assert productClass != null;
                Constructor<?> constructor = productClass.getDeclaredConstructor();
                constructor.setAccessible(true);
                Object instance = constructor.newInstance();

                List<Field> fields = new ArrayList<>();
                getFields(fields, productClass);
                for (Field field: fields) {
                    line = reader.readLine().trim();
                    field.setAccessible(true);
                    setField(instance, field, line.substring(line.indexOf('=')+1));
                }
                products.add(productClass.cast(instance));
            }

            while ((line = reader.readLine()) != null && !line.isBlank()) {
                line = line.trim();
                Class<Review> reviewClass = Review.class;

                Constructor<?> constructor = reviewClass.getDeclaredConstructor();
                constructor.setAccessible(true);
                Object instance = constructor.newInstance();

                List<Field> fields = new ArrayList<>();
                getFields(fields, reviewClass);
                for (Field field: fields) {
                    line = reader.readLine().trim();
                    field.setAccessible(true);
                    if (line.substring(0, line.indexOf('=')).equals("product")) {
                        Product product = getProductById(products, Integer.parseInt(line.substring(line.indexOf('=')+1)));
                        setField(instance, field, product);
                    } else {
                        setField(instance, field, line.substring(line.indexOf('=') + 1));
                    }
                }
                reviews.add(reviewClass.cast(instance));
            }
        } catch (Exception e) {
            Validator.showAlert(Alert.AlertType.ERROR, "File error", "Error while text file serialization!", "Check file info");
        }
    }

    static void setField(Object instance, Field field, Object value) throws IllegalAccessException {
        if (field.getType().equals(int.class)) {
            field.set(instance, Integer.parseInt(String.valueOf(value)));
        } else if (field.getType().equals(double.class)) {
            field.set(instance, Double.parseDouble(String.valueOf(value)));
        } else if (field.getType().equals(float.class)) {
            field.set(instance, Float.parseFloat(String.valueOf(value)));
        } else if (field.getType().isEnum()) {
            Enum<?> enumValue = Enum.valueOf((Class<? extends Enum>)field.getType(), String.valueOf(value));
            field.set(instance, enumValue);
        } else if (field.getType().equals(Product.class)) {
            field.set(instance, value);
        } else {
            field.set(instance, String.valueOf(value));
        }
    }

    private Product getProductById(List<Product> products, int id) {
        for (Product product: products) {
            if (product.getId() == id)
                return product;
        }
        return null;
    }

    private Class<?> getClassByName(List<ProductClass> classes, String name) {
        for (ProductClass productClass: classes) {
            if (productClass.getProductName().equals(name))
                return productClass.getProductClass();
        }
        return null;
    }
}
