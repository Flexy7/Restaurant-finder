package com.example.lukyn.toolbartest.RealmData;

import io.realm.RealmObject;

/**
 * Created by lukyn on 12.02.2018.
 */

public class RestaurantDailyMenu extends RealmObject {

    private String dish;
    private String price;


  

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
