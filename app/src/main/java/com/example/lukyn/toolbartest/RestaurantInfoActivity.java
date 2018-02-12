package com.example.lukyn.toolbartest;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lukyn.toolbartest.RealmData.RestaurantInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by lukyn on 12.02.2018.
 */

public class RestaurantInfoActivity extends AppCompatActivity {

    @BindView(R.id.activity_restaurant_infoButton)
    ImageView mapBtn;
    @BindView(R.id.activity_restaurant_infoViewAddress)
    TextView address;
    @BindView(R.id.activity_restaurant_infoViewAvgCost)
    TextView avgCost;
    @BindView(R.id.activity_restaurant_infoViewKitchenType)
    TextView kitchenType;
    @BindView(R.id.activity_restaurant_infoViewName)
    TextView name;
    @BindView(R.id.activity_restaurant_infoViewRating)
    TextView rating;
    @BindView(R.id.activity_restaurant_infoList)
    ListView list;


    Realm realm;
    DailyMenuAdapter adapter;
    RealmResults<RestaurantInfo> restaurantInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_info);
        Intent intent = getIntent();
        ButterKnife.bind(this);
        Realm.init(this);
        realm = Realm.getDefaultInstance();
        String value = intent.getStringExtra("restaurant");
        restaurantInfo = realm.where(RestaurantInfo.class).contains("name", value).findAll();
        if (!restaurantInfo.isEmpty()) {
            adapter = new DailyMenuAdapter(RestaurantInfoActivity.this, restaurantInfo.get(0).getDailyMenu());
            list.setAdapter(adapter);
        }
        name.setText(restaurantInfo.get(0).getName());
        address.setText(restaurantInfo.get(0).getAddress());
        avgCost.setText(restaurantInfo.get(0).getAvgCost());
        kitchenType.setText(restaurantInfo.get(0).getCuisines());
        rating.setText(restaurantInfo.get(0).getAggregate_rating());
        rating.setBackgroundColor(Color.parseColor(restaurantInfo.get(0).getRating_color()));

    }

    @OnClick(R.id.activity_restaurant_infoButton)
    public void onClick() {
        String longitude = restaurantInfo.get(0).getLongitude();
        String latitude = restaurantInfo.get(0).getLatitude();
        String coordinates = String.format("geo:0,0?q=" + latitude + "," + longitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(coordinates));
        startActivity(intent);
    }


}
