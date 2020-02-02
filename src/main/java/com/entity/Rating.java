package com.entity;

import java.util.Objects;

public class Rating {
    private int ratingId;
    private int speakerId;
    private int rating;


    public Rating(int ratingId,int speakerId, int rating) {
        this.ratingId = ratingId;
        this.speakerId = speakerId;
        this.rating = rating;
    }

    public int getRatingId() {
        return ratingId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getSpeakerId() {
        return speakerId;
    }

    public long getBonuses() {
        return  Math.round(rating*Math.sqrt(rating)*3);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating1 = (Rating) o;
        return ratingId == rating1.ratingId &&
                speakerId == rating1.speakerId &&
                rating == rating1.rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratingId, speakerId, rating);
    }
}
