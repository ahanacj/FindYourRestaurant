package com.apps.findyourrestaurant;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Restaurants.class}, version = 1, exportSchema = false)
public abstract class RestaurantRoomDatabase extends RoomDatabase {
    public abstract RestaurantsDao restaurantsDao();

    private static RestaurantRoomDatabase INSTANCE;

    public static RestaurantRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RestaurantRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RestaurantRoomDatabase.class, "res_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    private final RestaurantsDao mDao;
    String[] restaurant_name = {"WOW MOMO", "Chowman", "Honey Dhaba", "Burger King","KFC","Dominos","Taco Bell"};
    String[] restaurant_address={" C3, DC Block, Sector 1, Bidhannagar, Kolkata, West Bengal 700064",
    "BF-198, Sec-I, 5th Cross Rd, Bidhannagar, Kolkata, West Bengal 700064"," P-54, VIP Rd, Kankurgachi, Kolkata, West Bengal 700054",
    "DC 18, DC Block, Sector 1, Bidhannagar, Kolkata, West Bengal 700064","No 313 A,B & C, Mani Square Mall Mani Square Food Court, VIP Rd, Kolkata, West Bengal 700068",
    "Ground Floor, Gokul Banquets Building, Canal Street, 441, VIP Rd, Sreebhumi, Lake Town, Kolkata, West Bengal 700048",
    "3rd Floor, Unit No. 3A, Fine Dining, 32, Jagat Banerjee Ghat Road, Shibpur, Howrah, West Bengal 711102"};
    String[] restaurant_specialty={"Pan Fried Scheszwan Momo","Haka Chowmein","Butter Chicken","Chicken Whopper","Chicken Popcorn",
    "Pasta Pizza","Doritos Tacos"};
    double[] ratings={3.9,4.2,4.1,3.8,4.3,4.5,4.8};

    PopulateDbAsync(RestaurantRoomDatabase db) {
        mDao = db.restaurantsDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {
        // Start the app with a clean database every time.
        // Not needed if you only populate the database
        // when it is first created
        mDao.deleteAll();

        for (int i = 0; i <= restaurant_name.length - 1; i++) {
            Restaurants rest = new Restaurants(rest[i]);
            mDao.insert(rest);
        }
        return null;
    }
}
