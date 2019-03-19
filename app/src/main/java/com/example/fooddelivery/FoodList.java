package com.example.fooddelivery;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.fooddelivery.ui.foodlist.FoodListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodList extends AppCompatActivity {
    @BindView(R.id.navigation_view)
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list_activity);
        ButterKnife.bind(this);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, FoodListFragment.newInstance())
                .commitNow();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.home:
                    getSupportActionBar().setTitle("HOME");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, FoodListFragment.newInstance())
                            .commitNow();
                    return true;
                case R.id.cart:
                    getSupportActionBar().setTitle("CART");
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, CartFragment.newInstance())
                            .commitNow();
                    return true;
            }
            return false;
        }
    };

}
