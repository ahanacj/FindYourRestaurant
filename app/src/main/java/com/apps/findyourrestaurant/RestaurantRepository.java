package com.apps.findyourrestaurant;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import java.util.List;

public class RestaurantRepository {
    private RestaurantsDao mRestaurantDao;
    private LiveData<List<Restaurants>> mAllRestaurants;

    RestaurantRepository(Application application) {
        RestaurantRoomDatabase db = RestaurantRoomDatabase.getDatabase(application);
        mRestaurantDao = db.restaurantsDao();
        mAllRestaurants = mRestaurantDao.getAllRestaurants();
    }

    LiveData<List<Restaurants>> getAllWords() {
        return mAllRestaurants;
    }

    public void insert (Restaurants restaurant) {
        new insertAsyncTask(mRestaurantDao).execute(restaurant);
    }

    public LiveData<List<Restaurants>> getAllRestaurants() {
    }

    private static class insertAsyncTask extends AsyncTask<Restaurants, Void, Void> {

        private RestaurantsDao mAsyncTaskDao;

        insertAsyncTask(RestaurantsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Restaurants... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
