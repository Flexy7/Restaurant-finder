package com.example.lukyn.toolbartest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {
    Realm realm;
    SavedRestaurantsAdapter adapter;
    @BindView(R.id.activity_mainList)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        Realm.init(this);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        RealmResults<RestaurantInfo> restaurantInfo = realm.where(RestaurantInfo.class).findAll();
        adapter = new SavedRestaurantsAdapter(MainActivity.this, restaurantInfo);
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_m:
                super.onSearchRequested();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }


    @OnItemClick(R.id.activity_mainList)
    public void onItemClick(final int position) {
        Intent myIntent = new Intent(MainActivity.this, RestaurantInfoActivity.class);
        myIntent.putExtra("restaurant", adapter.getItem(position).getName());
        MainActivity.this.startActivity(myIntent);
    }

}

