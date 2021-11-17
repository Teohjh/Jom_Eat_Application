package com.example.jom_eat_2;
//old version of  food list page variable
public class Food {

    String name, hours, image, review;

    Food() {

    }

    public Food(String name, String hours, String image, String review) {
        this.name = name;
        this.hours = hours;
        this.image = image;
        this.review = review;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
