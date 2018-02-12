package com.example.lukyn.toolbartest;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lukyn.toolbartest.RealmData.RestaurantDailyMenu;
import com.example.lukyn.toolbartest.RealmData.RestaurantInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lukyn on 06.02.2018.
 */

public class SearchableActivity extends AppCompatActivity {
    @BindView(R.id.list2)
    ListView listView;
    private SearchedDataAdapter customAdapter;
    private String restaurantName;
    private Realm mRealm;
    private ZomatoClient client;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        ButterKnife.bind(this);

        mRealm = Realm.getDefaultInstance();

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            restaurantName = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(this, restaurantName, Toast.LENGTH_SHORT).show();
        }


        final Retrofit adapter = new Retrofit.Builder()
                .baseUrl("https://developers.zomato.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        client = adapter.create(ZomatoClient.class);
        Call<MainPojo> call = client.restaurantsCall(restaurantName);

        call.enqueue(new Callback<MainPojo>() {
            @Override
            public void onResponse(Call<MainPojo> call, Response<MainPojo> response) {
                List<Restaurants> list = response.body().getRestaurants();
                customAdapter = new SearchedDataAdapter(SearchableActivity.this, list);
                listView.setAdapter(customAdapter);
            }


            @Override
            public void onFailure(Call<MainPojo> call, Throwable t) {
                Toast.makeText(SearchableActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnItemClick(R.id.list2)
    public void onItemClick(final int position) {
        Call<Pojo> call = client.restaurantDailyMenuCall(16507624);

        call.enqueue(new Callback<Pojo>() {
            @Override
            public void onResponse(Call<Pojo> call, Response<Pojo> response) {
                List<DailyMenus> list = null;
                if (response.code() != 400) {
                    list = response.body().getDaily_menu();

                }
                saveRestaurantData(position, list);


                Intent myIntent = new Intent(SearchableActivity.this, RestaurantInfoActivity.class);
                myIntent.putExtra("restaurant", customAdapter.getItem(position).getRestaurant().getName());
                SearchableActivity.this.startActivity(myIntent);


            }


            @Override
            public void onFailure(Call<Pojo> call, Throwable t) {
                Toast.makeText(SearchableActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void saveRestaurantData(int position, List<DailyMenus> list) {
        mRealm.beginTransaction();

        RealmResults<RestaurantInfo> restaurant = mRealm.where(RestaurantInfo.class).equalTo("name", customAdapter.getItem(position).getRestaurant().getName()).findAll();
        if (!restaurant.isEmpty()) {
            restaurant.deleteAllFromRealm();

        }

        RestaurantInfo restaurantInfo = mRealm.createObject(RestaurantInfo.class);


        restaurantInfo.setName(customAdapter.getItem(position).getRestaurant().getName());
        restaurantInfo.setAvgCost(this.getString(R.string.cost, customAdapter.getItem(position).getRestaurant().getAvgCost(), customAdapter.getItem(position).getRestaurant().getCurrency()));
        Log.i("avgCost: ", "" + restaurantInfo.getAvgCost());
        restaurantInfo.setCuisines(customAdapter.getItem(position).getRestaurant().getCuisines());
        restaurantInfo.setAddress(customAdapter.getItem(position).getRestaurant().getLocation().getAddress());
        restaurantInfo.setLatitude(customAdapter.getItem(position).getRestaurant().getLocation().getLatitude());
        restaurantInfo.setLongitude(customAdapter.getItem(position).getRestaurant().getLocation().getLongitude());
        restaurantInfo.setAggregate_rating(customAdapter.getItem(position).getRestaurant().getUser_rating().getAggregate_rating());
        restaurantInfo.setRating_color(customAdapter.getItem(position).getRestaurant().getUser_rating().getRating_color());
        restaurantInfo.setCurrency(customAdapter.getItem(position).getRestaurant().getCurrency());
        RestaurantDailyMenu dailyMenu = new RestaurantDailyMenu();
        if (list != null) {

            for (int i = 0; i < list.size(); i++) {
                for (int a = 0; a < list.get(i).getDaily_menu().getDishes().size(); a++) {
                    dailyMenu.setDish(list.get(i).getDaily_menu().getDishes().get(a).getDish().getName());
                    dailyMenu.setPrice(list.get(i).getDaily_menu().getDishes().get(a).getDish().getPrice());
                    restaurantInfo.getDailyMenu().add(dailyMenu);
                }
            }

        }
        mRealm.commitTransaction();


    }

}
