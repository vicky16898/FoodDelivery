package com.example.fooddelivery;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseImplementation implements DatabaseInterface {
    Context context;
    public DatabaseHandler databaseHandler;
    public List<CartList> cartLists = new ArrayList<>();

    public DatabaseImplementation(Context context) {
        this.context = context;
        databaseHandler = new DatabaseHandler(context);
    }

    @Override
    public void insertData(CartList cartList) {
        long id = databaseHandler.insertNote(cartList.getId(), cartList.getOrdered_food_name(), cartList.getQuantity(), cartList.getCost());
        Log.d("id", String.valueOf(id));

    }

    @Override
    public boolean compareData(String foodName, int newQuantity) {
        cartLists = new ArrayList<>();
        cartLists = databaseHandler.getAllItems();
        for (int i = 0; i < cartLists.size(); i++) {
            if (foodName.equals(cartLists.get(i).getOrdered_food_name())) {
                if (cartLists.get(i).getQuantity() != newQuantity) {
                    cartLists.get(i).setQuantity(newQuantity);
                    databaseHandler.updateItem(cartLists.get(i));
                    return true;
                }


            }
        }
        return false;

    }

    @Override
    public boolean deleteData(String foodName) {
        cartLists = new ArrayList<>();
        cartLists = databaseHandler.getAllItems();
        for (int m = 0; m < cartLists.size(); m++) {
            if (foodName.equals(cartLists.get(m).getOrdered_food_name())) {
                databaseHandler.deleteItem(cartLists.get(m));
                Log.d("COUNT", String.valueOf(databaseHandler.getNotesCount()));
                return true;
            }
        }
        return false;
    }

    @Override
    public int getQuantity(String name) {
        cartLists = new ArrayList<>();
        cartLists = databaseHandler.getAllItems();
        for (int j = 0; j < cartLists.size(); j++) {
            if(name.equals(cartLists.get(j).getOrdered_food_name())){
                return cartLists.get(j).getQuantity();
            }
        }
        return 0;
    }
}
