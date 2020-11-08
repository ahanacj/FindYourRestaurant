package com.apps.findyourrestaurant;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

public interface RestaurantsDao {


    LiveData<List<Restaurants>> getAllRestaurants();

    @Dao
    public interface RestaurantsDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(Restaurants restaurant);

        @Query("DELETE FROM restaurants_table")
        void deleteAll();

        @Query("SELECT * from restaurants_table ORDER BY res_name ASC")
        LiveData<List<Restaurants>> getAllRestaurants();

    }
}
