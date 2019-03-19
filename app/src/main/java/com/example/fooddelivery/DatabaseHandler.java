package com.example.fooddelivery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "cart_db";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CartList.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertNote(int id, String foodName, int quantity, String cost) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(CartList.COLUMN_ID, id);
        values.put(CartList.FOOD_NAME, foodName);
        values.put(CartList.QUANTITY, quantity);
        values.put(CartList.COST, cost);

        // insert row
        long idStored = db.insert(CartList.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public List<CartList> getAllItems() {
        List<CartList> cartLists = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + CartList.TABLE_NAME;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CartList cartList = new CartList();
                cartList.setId(cursor.getInt(cursor.getColumnIndex(CartList.COLUMN_ID)));
                cartList.setOrdered_food_name(cursor.getString(cursor.getColumnIndex(CartList.FOOD_NAME)));
                cartList.setQuantity(cursor.getInt(cursor.getColumnIndex(CartList.QUANTITY)));
                cartList.setCost(cursor.getString(cursor.getColumnIndex(CartList.COST)));
                cartLists.add(cartList);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return cartLists;
    }

    public int updateItem(CartList cartList) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CartList.QUANTITY, cartList.getQuantity());

        // updating row
        return db.update(CartList.TABLE_NAME, values, CartList.COLUMN_ID + " = ?",
                new String[]{String.valueOf(cartList.getId())});
    }

    public void deleteItem(CartList cartList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CartList.TABLE_NAME, CartList.COLUMN_ID + " = ?",
                new String[]{String.valueOf(cartList.getId())});
        db.close();
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + CartList.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }
}
