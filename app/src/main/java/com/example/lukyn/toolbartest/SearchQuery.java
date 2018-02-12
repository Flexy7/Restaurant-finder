package com.example.lukyn.toolbartest;

import java.util.List;

/**
 * Created by lukyn on 07.02.2018.
 */

class MainPojo {
    int results_found;
    int results_start;
    int results_shown;


    String name;

    public String getName() {
        return name;
    }


    public List<Restaurants> restaurants;


    public int getResults_found() {
        return results_found;
    }

    public int getResults_start() {
        return results_start;
    }

    public int getResults_shown() {
        return results_shown;
    }

    public List<Restaurants> getRestaurants() {
        return restaurants;
    }


}

class Restaurants {
    private Restaurant restaurant;


    public Restaurant getRestaurant() {
        return restaurant;
    }

}

class Restaurant {

    private String name;
    private String currency;
    private Location location;
    private Rating user_rating;
    private String average_cost_for_two;
    private String cuisines;
    private String id;

    public String getCurrency() {
        return currency;
    }

    public String getCuisines() {
        return cuisines;
    }

    public String getName() {
        return name;
    }

    public String getAvgCost() {
        return average_cost_for_two;
    }

    public Location getLocation() {
        return location;
    }

    public Rating getUser_rating() {
        return user_rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}


class Location {


    private String address;
    private String city;
    private String latitude;
    private String longitude;

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

}

class Rating {

    private String aggregate_rating;
    private String rating_color;

    public String getAggregate_rating() {
        return aggregate_rating;
    }

    public String getRating_color() {

        return "#" + rating_color;
    }

}








