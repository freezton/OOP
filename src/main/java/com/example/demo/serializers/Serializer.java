package com.example.demo.serializers;

import com.example.demo.classes.Product;
import com.example.demo.classes.Review;

import java.util.List;

public interface Serializer {


    byte[] serialize(List<Product> products, List<Review> reviews);

    void deserialize(List<Product> products, List<Review> reviews, byte[] data);
}
