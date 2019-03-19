package com.example.fooddelivery;

import android.graphics.Bitmap;

public class FoodModel {
    private String foodName;
    private Bitmap foodImage;
    private String cost;

    public FoodModel(String foodName, Bitmap foodImage, String cost) {
        this.foodName = foodName;
        this.foodImage = foodImage;
        this.cost = cost;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodImage(Bitmap foodImage) {
        this.foodImage = foodImage;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getFoodName() {
        return foodName;
    }

    public Bitmap getFoodImage() {
        return foodImage;
    }

    public String getCost() {
        return cost;
    }
}
