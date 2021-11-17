package com.example.jom_eat_2;

//home page category recycler view

public class Category {

    private String category;
    private String img_icon;

    public Category(String category, String img_icon) {
        this.category = category;
        this.img_icon = img_icon;
    }

    public String getCategory() {
        return category;
    }

    public void setTitle(String category) {
        this.category = category;
    }

    public String getImg_icon() {
        return img_icon;
    }

    public void setImg_icon(String img_icon) {
        this.img_icon = img_icon;
    }
}
