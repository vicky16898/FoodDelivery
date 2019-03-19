package com.example.fooddelivery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartListArrayAdapter extends ArrayAdapter {
    Context context;
    List<CartList> cartLists = new ArrayList<>();
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.list_quantity)
    TextView list_quantity;
    @BindView(R.id.price)
    TextView price;

    public CartListArrayAdapter(Context context, List<CartList> cartLists) {
        super(context, 0, cartLists);
        this.cartLists = cartLists;
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        ButterKnife.bind(this, listItem);

        CartList cartList = cartLists.get(position);
        name.setText(cartList.getOrdered_food_name());
        int cost = Integer.parseInt(cartList.getCost());
        int due = cost*(cartList.getQuantity());
        price.append(String.valueOf(due));
        list_quantity.setText(String.valueOf(cartList.getQuantity()));




        return listItem;
    }
}
