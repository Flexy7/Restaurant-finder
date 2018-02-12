package com.example.lukyn.toolbartest.RealmData;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by lukyn on 10.02.2018.
 */

public class RestaurantInfo extends RealmObject {

    private String name;
    private String currency;
    private String avgCost;
    private String cuisines;
    private String address;
    private String city;
    private String latitude;
    private String longitude;
    private String aggregate_rating;


    private RealmList<RestaurantDailyMenu> dailyMenu;

    public RealmList<RestaurantDailyMenu> getDailyMenu() {
        return dailyMenu;
    }

    public void setDailyMenu(RealmList<RestaurantDailyMenu> dailyMenu) {
        this.dailyMenu = dailyMenu;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAvgCost() {
        return avgCost;
    }

    public void setAvgCost(String avgCost) {
        this.avgCost = avgCost;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAggregate_rating() {
        return aggregate_rating;
    }

    public void setAggregate_rating(String aggregate_rating) {
        this.aggregate_rating = aggregate_rating;
    }

    public String getRating_color() {
        return rating_color;
    }

    public void setRating_color(String rating_color) {
        this.rating_color = rating_color;
    }

    String rating_color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}




