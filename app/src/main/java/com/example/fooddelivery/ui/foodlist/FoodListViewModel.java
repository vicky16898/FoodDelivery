package com.example.fooddelivery.ui.foodlist;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.fooddelivery.FoodList;
import com.example.fooddelivery.FoodModel;
import com.example.fooddelivery.R;

import java.util.ArrayList;
import java.util.List;


public class FoodListViewModel extends ViewModel {
    public Bitmap bir, shaw, crunchy, dev, milk, turkish, alf;
    public List<FoodModel> foodModelList = new ArrayList<>();

    public void init(Context context) {
        bir = BitmapFactory.decodeResource(context.getResources(), R.drawable.bir);
        shaw = BitmapFactory.decodeResource(context.getResources(), R.drawable.shaw);
        crunchy = BitmapFactory.decodeResource(context.getResources(), R.drawable.crunchy);
        dev = BitmapFactory.decodeResource(context.getResources(), R.drawable.dev);
        milk = BitmapFactory.decodeResource(context.getResources(), R.drawable.milkshake);
        turkish = BitmapFactory.decodeResource(context.getResources(), R.drawable.turkish);
        alf = BitmapFactory.decodeResource(context.getResources(), R.drawable.alfaham);

    }

    // TODO: Implement the ViewModel
    public void populateList() {
        foodModelList.add(new FoodModel("Chicken Biryani", bir, "168"));
        foodModelList.add(new FoodModel("Shawarma", shaw, "70"));
        foodModelList.add(new FoodModel("Crunchy Frappe", crunchy, "56"));
        foodModelList.add(new FoodModel("Deviled Chicken Wings", dev, "99"));
        foodModelList.add(new FoodModel("Chocolate Milkshake", milk, "50"));
        foodModelList.add(new FoodModel("Turkish Delight", turkish, "85"));
        foodModelList.add(new FoodModel("Alfaham", alf, "90"));

    }

    public List<FoodModel> getFoodModelList() {
        return foodModelList;
    }
}
