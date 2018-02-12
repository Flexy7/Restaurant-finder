package com.example.lukyn.toolbartest;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lukyn.toolbartest.RealmData.RestaurantInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lukyn on 12.02.2018.
 */

public class SavedRestaurantsAdapter extends ArrayAdapter<RestaurantInfo> {

    private Context context;
    private List<RestaurantInfo> values;
    private RestaurantInfo item;
    @BindView(R.id.activity_restaurant_infoViewName)
    TextView restaurantName;
    @BindView(R.id.activity_restaurant_infoViewAddress)
    TextView restaurantAddress;
    @BindView(R.id.activity_restaurant_infoViewRating)
    TextView restaurantRating;
    @BindView(R.id.activity_restaurant_infoViewKitchenType)
    TextView restaurantCuisines;
    @BindView(R.id.activity_restaurant_infoViewAvgCost)
    TextView restaurantAvgCost;


    public SavedRestaurantsAdapter(Context context, List<RestaurantInfo> values) {
        super(context, R.layout.restaurant_item, values);
        this.context = context;
        this.values = values;
        Log.i("adapter:: ", "" + values.size());

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.restaurant_item, parent, false);
        }
        item = values.get(position);
        ButterKnife.bind(this, row);
        restaurantName.setText(item.getName());
        restaurantAddress.setText(item.getAddress());
        restaurantAvgCost.setText(item.getAvgCost());
        restaurantCuisines.setText(item.getCuisines());
        restaurantRating.setText(item.getAggregate_rating());
        restaurantRating.setBackgroundColor(Color.parseColor(item.getRating_color()));

        return row;

    }
}