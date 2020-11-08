package com.apps.findyourrestaurant;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "restaurants_table")
public class Restaurants{
    @PrimaryKey(autoGenerate=true)
    public int res_id;

    @ColumnInfo(name = "res_name")
    public String name;

    @ColumnInfo(name = "address")
    public String address;

    @ColumnInfo(name = "speciality")
    public String speciality;

    @ColumnInfo(name = "rating")
    public double rating;

}
