package com.example.demo.serializers;

import com.example.demo.classes.Review;

import java.util.List;

public class ReviewListWrapper {

    private List<Review> reviews;

    public ReviewListWrapper() {
    }

    public ReviewListWrapper(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
