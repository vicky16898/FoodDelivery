package com.example.fooddelivery;

public interface DatabaseInterface {
    public void insertData(CartList cartList);

    public boolean compareData(String foodName, int Quantity);

    public boolean deleteData(String foodName);

    public int getQuantity(String name);
}
