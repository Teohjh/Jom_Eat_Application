package com.example.jom_eat_2;

public class CommentModel
{
    String comment ,  userImage;
    Double rating;

    public CommentModel() {
    }

    public CommentModel(String comment, Double rating , String userImage)
    {
        this.comment = comment;
        this.rating = rating;
        this.userImage = userImage;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
