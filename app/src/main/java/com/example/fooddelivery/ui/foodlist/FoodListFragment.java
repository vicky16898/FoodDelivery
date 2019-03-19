package com.example.fooddelivery.ui.foodlist;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.example.fooddelivery.CustomAdapter;
import com.example.fooddelivery.FoodModel;
import com.example.fooddelivery.R;
import com.example.fooddelivery.ScalingAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodListFragment extends Fragment {

    private FoodListViewModel mViewModel;
    private List<FoodModel> foodModelList = new ArrayList<FoodModel>();
    private CustomAdapter customAdapter;


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    public static FoodListFragment newInstance() {
        return new FoodListFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.food_list_fragment, container, false);
        ButterKnife.bind(this, view);



        ScalingAnimation scalingAnimation = new ScalingAnimation(getContext(), new DecelerateInterpolator(), 0.2f);
        recyclerView.setLayoutManager(scalingAnimation);


        customAdapter = new CustomAdapter(getContext(), foodModelList);
        recyclerView.setAdapter(customAdapter);

        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FoodListViewModel.class);
        // TODO: Use the ViewModel
        mViewModel.init(getContext());
        mViewModel.populateList();
        foodModelList.clear();
        foodModelList.addAll(mViewModel.getFoodModelList());
        customAdapter.update(foodModelList);


    }


}
