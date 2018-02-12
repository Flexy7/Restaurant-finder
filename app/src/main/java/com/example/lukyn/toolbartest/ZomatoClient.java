package com.example.lukyn.toolbartest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by lukyn on 06.02.2018.
 */


public interface ZomatoClient {

    @Headers({
            "Accept: application/json",
            "user-key: 2b3a8c2baa6d953047bc375668d2988a"
    })

    @GET("api/v2.1/search?entity_id=1&entity_type=city")
    Call<MainPojo> restaurantsCall(@Query("q") String restaurantName );

    @Headers({
            "Accept: application/json",
            "user-key: 2b3a8c2baa6d953047bc375668d2988a"
    })
    @GET("api/v2.1/dailymenu?")
    Call<Pojo> restaurantDailyMenuCall(@Query("res_id") int restaurantId );

}




