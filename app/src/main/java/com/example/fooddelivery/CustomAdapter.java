package com.example.fooddelivery;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPickerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<FoodModel> foodModelList;
    private Context context;
    private DatabaseInterface databaseImplementation;
    private int id = 0;


    public CustomAdapter(Context context, List<FoodModel> foodModelList) {
        this.context = context;
        this.foodModelList = foodModelList;
        databaseImplementation = new DatabaseImplementation(context);
        SharedPreferences settings = context.getSharedPreferences("ID_STORAGE", 0);
        id = settings.getInt("ID_", 0);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        final FoodModel foodModel = foodModelList.get(i);
        myViewHolder.foodName.setText(foodModel.getFoodName());
        myViewHolder.cost.append(foodModel.getCost());
        myViewHolder.foodImage.setImageBitmap(foodModel.getFoodImage());
        myViewHolder.numberPicker.setVisibility(View.INVISIBLE);
        int q = databaseImplementation.getQuantity(foodModelList.get(i).getFoodName());
        if (q != 0) {
            myViewHolder.numberPicker.setValue(q);
            myViewHolder.numberPicker.setVisibility(View.VISIBLE);
            myViewHolder.textView.setVisibility(View.INVISIBLE);
            myViewHolder.addToCart.setEnabled(false);
            myViewHolder.addToCart.setClickable(false);
        }
        myViewHolder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myViewHolder.textView.setVisibility(View.INVISIBLE);
                myViewHolder.numberPicker.setVisibility(View.VISIBLE);
                myViewHolder.numberPicker.setValue(1);
                id++;
                SharedPreferences settings = context.getSharedPreferences("ID_STORAGE", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("ID_", id);
                editor.apply();
                databaseImplementation.insertData(new CartList(id,
                        myViewHolder.foodName.getText().toString(),
                        myViewHolder.numberPicker.getValue(),
                        foodModel.getCost()));
            }
        });

        myViewHolder.numberPicker.setListener(new ScrollableNumberPickerListener() {
            @Override
            public void onNumberPicked(int value) {
                if (myViewHolder.numberPicker.getValue() != 0) {
                    boolean compare = databaseImplementation.compareData(foodModelList.get(i).getFoodName(), myViewHolder.numberPicker.getValue());
                    Log.d("compare", String.valueOf(compare));

                } else {
                    myViewHolder.numberPicker.setVisibility(View.INVISIBLE);
                    myViewHolder.textView.setVisibility(View.VISIBLE);
                    boolean deleteData = databaseImplementation.deleteData(foodModelList.get(i).getFoodName());
                    Log.d("deleted", String.valueOf(deleteData));
                    id--;
                    SharedPreferences settings = context.getSharedPreferences("ID_STORAGE", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putInt("ID_", id);
                    editor.apply();

                }

            }
        });
    }

    public void update(List<FoodModel> newList) {
        foodModelList = new ArrayList<>();
        this.foodModelList.addAll(newList);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return foodModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.food_name)
        TextView foodName;
        @BindView(R.id.cost)
        TextView cost;
        @BindView(R.id.add_to_cart)
        RelativeLayout addToCart;
        @BindView(R.id.food_image)
        ImageView foodImage;
        @BindView(R.id.number_picker)
        ScrollableNumberPicker numberPicker;
        @BindView(R.id.add_text)
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                foodImage.setClipToOutline(true);
            }
        }
    }
}