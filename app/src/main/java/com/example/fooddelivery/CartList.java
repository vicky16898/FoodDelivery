package com.example.fooddelivery;

public class CartList {
    private int id;
    private String ordered_food_name;
    private int quantity;
    private String cost;

    public static final String TABLE_NAME = "cart";

    public static final String COLUMN_ID = "id";
    public static final String FOOD_NAME = "food_name";
    public static final String QUANTITY = "quantity";
    public static final String COST = "cost";

    public void setId(int id) {
        this.id = id;
    }

    public CartList() {

    }

    public CartList(int id, String ordered_food_name, int quantity, String cost) {
        this.id = id;
        this.ordered_food_name = ordered_food_name;
        this.quantity = quantity;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER,"
                    + FOOD_NAME + " TEXT,"
                    + QUANTITY + " INTEGER,"
                    + COST + " TEXT"
                    + ")";

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setOrdered_food_name(String ordered_food_name) {
        this.ordered_food_name = ordered_food_name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrdered_food_name() {
        return ordered_food_name;
    }

    public int getQuantity() {
        return quantity;
    }


}
