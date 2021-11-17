package com.example.jom_eat_2;

//food activity
public class FoodModel
{
    //variable
    String title ,image, hours,address, phone,order;
    Double review;

    //for store firebase database
    FoodModel() {
    }

    //constructor
    public FoodModel(String title, String image, String hours, String address,  String phone, String order,double review) {
        this.title = title;
        this.review = review;
        this.hours = hours;
        this.image = image;
        this.address = address;
        this.phone = phone;
        this.order = order;
        //this.category = category;
    }

    //getter and setter
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public Double getReview() {
        return review;
    }

    public void setReview(Double review) {
        this.review = review;
    }

   // public String getCategory() { return category; }

    //public void setCategory(String category) { this.category = category; }


    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getOrder() { return order; }

    public void setOrder(String order) { this.order = order; }
}
