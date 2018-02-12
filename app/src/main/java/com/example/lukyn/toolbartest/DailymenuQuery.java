package com.example.lukyn.toolbartest;

import java.util.List;

/**
 * Created by lukyn on 11.02.2018.
 */

class Pojo {
    private  List<DailyMenus> daily_menus;


    public List<DailyMenus> getDaily_menu() {
        return daily_menus;
    }

    public void setDaily_menu(List<DailyMenus> daily_menu) {
        this.daily_menus = daily_menu;
    }
}

class DailyMenus {


    private DailyMenu daily_menu;

    public DailyMenu getDaily_menu() {
        return daily_menu;
    }

    public void setDaily_menu(DailyMenu daily_menu) {
        this.daily_menu = daily_menu;
    }

}

class DailyMenu{

    private String name;
    private List<Dishes> dishes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dishes> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dishes> dishes) {
        this.dishes = dishes;
    }
}


class Dishes {
    private Dish dish;

    public Dish getDish() {
        return dish;
    }


}

class Dish {

    private String name;
    private String price;


    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }


}

