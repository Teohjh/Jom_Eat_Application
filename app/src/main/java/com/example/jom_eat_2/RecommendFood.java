package com.example.jom_eat_2;

public class RecommendFood {

    private String title_rcmd;
    private String img_rcmd_icon;
    private String m_tv_food_rcmd_review;

    public RecommendFood(String title_rcmd, String img_rcmd_icon, String m_tv_food_rcmd_review) {
        this.title_rcmd = title_rcmd;
        this.img_rcmd_icon = img_rcmd_icon;
        this.m_tv_food_rcmd_review = m_tv_food_rcmd_review;
    }

    public String getTitle_rcmd() {
        return title_rcmd;
    }

    public void setTitle_rcmd(String title_rcmd) {
        this.title_rcmd = title_rcmd;
    }

    public String getImg_rcmd_icon() {
        return img_rcmd_icon;
    }

    public void setImg_rcmd_icon(String img_rcmd_icon) {
        this.img_rcmd_icon = img_rcmd_icon;
    }

    public String getM_tv_food_rcmd_review() {
        return m_tv_food_rcmd_review;
    }

    public void setM_tv_food_rcmd_review(String m_tv_food_rcmd_review) {
        this.m_tv_food_rcmd_review = m_tv_food_rcmd_review;
    }
}
