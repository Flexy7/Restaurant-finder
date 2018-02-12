package com.example.lukyn.toolbartest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lukyn.toolbartest.RealmData.RestaurantDailyMenu;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lukyn on 12.02.2018.
 */

public class DailyMenuAdapter extends ArrayAdapter<RestaurantDailyMenu> {

    private Context context;
    private List<RestaurantDailyMenu> values;
    private RestaurantDailyMenu item;
    @BindView(R.id.simple_itemViewDish)
    TextView dish;
    @BindView(R.id.simple_itemViewCost)
    TextView cost;

    public DailyMenuAdapter(Context context, List<RestaurantDailyMenu> values) {
        super(context, R.layout.simple_item, values);
        this.context = context;
        this.values = values;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.simple_item, parent, false);
        }
        ButterKnife.bind(this, row);

        item = values.get(position);


        dish.setText(item.getDish());
        cost.setText(item.getPrice());


        return row;
    }


}
