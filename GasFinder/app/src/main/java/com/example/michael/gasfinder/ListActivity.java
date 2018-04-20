package com.example.michael.gasfinder;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class ListActivity extends AppCompatActivity {

    private LinearLayout gasList;

    private Spinner fuelSpinner, orderSpinner;
    private TextView radius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        fuelSpinner = findViewById(R.id.fuelSpinner);
        orderSpinner = findViewById(R.id.orderSpinner);
        gasList = findViewById(R.id.gas_items);
        radius = findViewById(R.id.radius);

        String[] fuelTypes = new String[]{"Unleaded", "Plus", "Premium", "Diesel"};
        ArrayAdapter fuelAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, fuelTypes
        );
        fuelSpinner.setAdapter(fuelAdapter);

        String[] orderTypes = new String[]{"Distance", "Price"};
        ArrayAdapter orderAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, orderTypes
        );
        orderSpinner.setAdapter(orderAdapter);
    }

    public void displayStations(JSONArray arr) {
        String fuelType = fuelSpinner.getSelectedItem().toString();
        for (int i = 0; i < arr.length(); i++) {
            try {
                JSONObject o = arr.getJSONObject(i);
                GasStation station = new GasStation(o);

                if (i < 20) {
                    addRow(
                            station.getStationName(),
                            station.getDistance(),
                            fuelType,
                            station.getFuelPrice(fuelType),
                            station.getAddress());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void addRow(
            String name, String distance, String fuelType, String price, String address) {
        LinearLayout outsideLayout = new LinearLayout(this);

        LinearLayout.LayoutParams outsideParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f
        );
        outsideParams.setMargins(40, 40, 40, 0);
        outsideLayout.setLayoutParams(outsideParams);
        outsideLayout.setBackgroundResource(R.drawable.fragment_style);
        outsideLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout textLayout = addText(name, distance, fuelType, price, address);

        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                350, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f
        );
        imageParams.setMargins(20, 0, 0, 0);

        ImageView gasStationIcon = new ImageView(this);
        gasStationIcon.setAdjustViewBounds(true);
        gasStationIcon.setLayoutParams(imageParams);
        gasStationIcon.setImageResource(setImageView(name));
        outsideLayout.addView(gasStationIcon);
        outsideLayout.addView(textLayout);

        gasList.addView(outsideLayout);
    }

    private LinearLayout addText(
            String name, String distance, String fuelType, String price, String address) {
        LinearLayout textLayout = new LinearLayout(this);

        LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 3.0f
        );
        textLayoutParams.setMargins(30, 0, 0, 0);
        textLayout.setLayoutParams(textLayoutParams);
        textLayout.setOrientation(LinearLayout.VERTICAL);

        TextView stationName = new TextView(this);
        stationName.setText(name);
        textLayout.addView(stationName);
        TextView travelDistance = new TextView(this);
        travelDistance.setText("Distance: " + distance);
        textLayout.addView(travelDistance);
        TextView typePrice = new TextView(this);
        typePrice.setText(fuelType + ": $" + price );
        textLayout.addView(typePrice);
        TextView addressText = new TextView(this);
        addressText.setText(address);
        textLayout.addView(addressText);

        return textLayout;
    }

    private String queryFuelString(String value) {
        switch (value) {
            case "Unleaded":
                return "reg";
            case "Plus":
                return "mid";
            case "Premium":
                return "pre";
            case "Diesel":
                return "diesel";
            default:
                return "";
        }
    }

    private Integer setImageView(String stationName) {
        switch (stationName) {
            case "Fasmart":
                return R.drawable.fasmartlogo;
            case "7-Eleven":
                return R.drawable.sevenelevenlogo;
            case "Liberty":
                return R.drawable.libertylogo;
            case "BP":
                return R.drawable.bplogo;
            case "Sunoco":
                return R.drawable.suncologo;
            case "Wilcohess":
                return R.drawable.hesslogo;
            case "Marathon":
                return R.drawable.marathonlogo;
            case "Sheetz":
                return R.drawable.sheetzlogo;
            case "Exxon":
                return R.drawable.exxonlogo;
            case "Valero":
                return R.drawable.valerologo;
            case "Kroger":
                return R.drawable.krogerlogo;
            case "Citgo":
                return R.drawable.citigologo;
            default:
                return R.drawable.defaultlogo;
        }
    }
}
