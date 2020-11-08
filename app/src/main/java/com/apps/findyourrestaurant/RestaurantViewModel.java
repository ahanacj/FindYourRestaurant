package com.apps.findyourrestaurant;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RestaurantViewModel extends AndroidViewModel {
    private final Restaurants restaurant;
    private RestaurantRepository mRepository;
    private LiveData<List<Restaurants>> mAllRestaurants;

    public RestaurantViewModel (Application application) {
        super(application);
        mRepository = new RestaurantRepository(application);
        mAllRestaurants = mRepository.getAllRestaurants();
        LiveData<List<Restaurants>> getAllRestaurants() { return mAllRestaurants; }
        public void insert(Restaurants restaurant) ;
        { mRepository.insert(restaurant); }
    }
}

