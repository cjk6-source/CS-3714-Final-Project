package com.example.michael.gasfinder;

import org.json.JSONObject;

/**
 * Created by Tay on 4/17/2018.
 */

public class GasStation {

    private String reg_price, mid_price, prem_price, diesel_price;
    private String reg_date, mid_date, prem_date, diesel_date;
    private String address;
    private Double latitude, longitude;
    private String stationName;
    private String distance;
    private String region, city;

    private int id;

    public GasStation(JSONObject o) {
        try {
            reg_price = o.getString("reg_price");
            mid_price = o.getString("mid_price");
            prem_price = o.getString("pre_price");
            diesel_price = o.getString("diesel_price");
            reg_date = o.getString("reg_date");
            mid_date = o.getString("pre_date");
            prem_date = o.getString("pre_date");
            diesel_date = o.getString("diesel_date");
            address = o.getString("address");
            id = o.getInt("id");
            latitude = o.getDouble("lat");
            longitude = o.getDouble("lng");
            stationName = o.getString("station");
            distance = o.getString("distance");
            region = o.getString("region");
            city = o.getString("city");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getReg_price() {
        return reg_price;
    }

    public String getMid_price() {
        return mid_price;
    }

    public String getPrem_price() {
        return prem_price;
    }

    public String getDiesel_price() {
        return diesel_price;
    }

    public String getReg_date() {
        return reg_date;
    }

    public String getMid_date() {
        return mid_date;
    }

    public String getPrem_date() {
        return prem_date;
    }

    public String getDiesel_date() {
        return diesel_date;
    }

    public String getAddress() {
        return address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getStationName() {
        return stationName;
    }

    public int getId() {
        return id;
    }

    public String getDistance() {
        return distance;
    }

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }
}
