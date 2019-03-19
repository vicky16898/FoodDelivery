package com.example.fooddelivery;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartFragment extends Fragment {

    private CartViewModel mViewModel;
    @BindView(R.id.list)
    ListView listView;
    @BindView(R.id.empty_cart)
    TextView emptyCart;
    private List<CartList> cartLists = new ArrayList<>();
    private DatabaseHandler databaseHandler;
    private CartListArrayAdapter cartListArrayAdapter;

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_fragment, container, false);
        ButterKnife.bind(this, view);
        databaseHandler = new DatabaseHandler(getActivity());
        cartLists = databaseHandler.getAllItems();
        if(cartLists.size() == 0){

            emptyCart.setVisibility(View.VISIBLE);

        }
        cartListArrayAdapter = new CartListArrayAdapter(getContext(), cartLists);
        listView.setAdapter(cartListArrayAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        // TODO: Use the ViewModel
    }

}
