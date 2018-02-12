package com.example.lukyn.toolbartest;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by lukyn on 06.02.2018.
 */

public class SearchedDataAdapter extends ArrayAdapter<Restaurants> {
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


    private Context context;
    private List<Restaurants> values;
    private Restaurants item;


    public SearchedDataAdapter(Context context, List<Restaurants> values) {
        super(context, R.layout.restaurant_item, values);
        this.context = context;
        this.values = values;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.restaurant_item, parent, false);
        }
        ButterKnife.bind(this, row);


        item = values.get(position);
        restaurantName.setText(item.getRestaurant().getName());
        restaurantAddress.setText(item.getRestaurant().getLocation().getAddress());
        restaurantRating.setText(item.getRestaurant().getUser_rating().getAggregate_rating());
        restaurantRating.setBackgroundColor(Color.parseColor(item.getRestaurant().getUser_rating().getRating_color()));
        restaurantCuisines.setText(item.getRestaurant().getCuisines());
        restaurantAvgCost.setText(context.getString(R.string.cost, item.getRestaurant().getAvgCost(), item.getRestaurant().getCurrency()));
        Log.i("color", item.getRestaurant().getUser_rating().getRating_color());
        return row;
    }


}
