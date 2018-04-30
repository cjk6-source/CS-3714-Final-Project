package com.example.michael.gasfinder;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListActivity extends AppCompatActivity {

    private LinearLayout gasList;
    ArrayList<GasStation> nearbyStations;

    private Spinner fuelSpinner, orderSpinner;
    private TextView radius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        nearbyStations = (ArrayList<GasStation>) bundle.getSerializable(GasFinder.NEARBY_STATIONS);

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


        displayStations(nearbyStations);

    }

    public void findStations(View view) {
        Log.d("yee", Integer.toString(nearbyStations.size()));
        gasList.removeAllViews();
        displayStations(nearbyStations);
    }

    public void displayStations(ArrayList<GasStation> myStations) {
        if (myStations.size() != 0) {
            Double distance = Double.parseDouble(radius.getText().toString());
            String order = orderSpinner.getSelectedItem().toString();
            String fuelType = fuelSpinner.getSelectedItem().toString();

            if (order.toLowerCase().equals("distance")) {
                Collections.sort(myStations, new compareDistance());
            } else {
                Collections.sort(myStations, new comparePrice());
            }

            ArrayList<GasStation> distanceFilter = new ArrayList<>();

            for (int i = 0; i < myStations.size(); i++) {
                if (myStations.get(i).getDoubleDistance() <= distance) {
                    distanceFilter.add(myStations.get(i));
                }
            }

            int count = 0;
            for (GasStation station : distanceFilter) {
                if (count > 30) {
                    break;
                }
                addRow(
                        station.getStationName(),
                        station.getDistance(),
                        fuelType,
                        station.getFuelPrice(fuelType),
                        station.getAddress(),
                        count);
                count++;
            }
        }
    }

    private void addRow(
            String name, String distance, String fuelType, String price, String address, int index) {
        LinearLayout outsideLayout = new LinearLayout(this);

        LinearLayout.LayoutParams outsideParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f
        );
        outsideParams.setMargins(40, 40, 40, 0);
        outsideLayout.setLayoutParams(outsideParams);
        outsideLayout.setBackgroundResource(R.drawable.fragment_style);
        outsideLayout.setOrientation(LinearLayout.HORIZONTAL);
        int s = index;
        LinearLayout textLayout = addText(name, distance, fuelType, price, address, s);

        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                350, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f
        );
        imageParams.setMargins(20, 0, 0, 0);

        ImageView gasStationIcon = new ImageView(this);
        gasStationIcon.setAdjustViewBounds(true);
        gasStationIcon.setLayoutParams(imageParams);
        gasStationIcon.setImageResource(setImageView(name));
        nearbyStations.get(s).setMarker(setImageView(name));
        outsideLayout.addView(gasStationIcon);
        outsideLayout.addView(textLayout);

        gasList.addView(outsideLayout);
    }

    private LinearLayout addText(
            String name, String distance, String fuelType, String price, final String address, int index) {
        LinearLayout textLayout = new LinearLayout(this);

        LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 3.0f
        );
        textLayoutParams.setMargins(30, 0, 0, 0);
        textLayout.setLayoutParams(textLayoutParams);
        textLayout.setOrientation(LinearLayout.VERTICAL);
        final int z = index;
        TextView stationName = new TextView(this);
        stationName.setText(name);
        stationName.setTextSize(20);
        stationName.setTextColor(getResources().getColor(R.color.textOrange));
        textLayout.addView(stationName);
        TextView travelDistance = new TextView(this);
        travelDistance.setText("Distance: " + distance);
        textLayout.addView(travelDistance);
        TextView typePrice = new TextView(this);
        typePrice.setText(fuelType + ": $" + price);
        textLayout.addView(typePrice);
        TextView addressText = new TextView(this);
        addressText.setText(address);
        textLayout.addView(addressText);

        textLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewStation(z);
            }
        });

        return textLayout;
    }

    private void onViewStation(int z) {
        Intent intent = new Intent(this, DetailsScreen.class);
        intent.putExtra("GAS STATION OBJECT", nearbyStations.get(z));
        startActivity(intent);
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

    private class compareDistance implements Comparator<GasStation> {

        @Override
        public int compare(GasStation station, GasStation t1) {
            if (station.getDoubleDistance() < t1.getDoubleDistance()) {
                return -1;
            } else if (station.getDoubleDistance() > t1.getDoubleDistance()) {
                return 1;
            }
            return 0;
        }
    }

    private class comparePrice implements Comparator<GasStation> {
        String fuelType = fuelSpinner.getSelectedItem().toString();
        @Override
        public int compare(GasStation station, GasStation t1) {
            if (fuelType.toLowerCase().equals("unleaded")) {

                Double p1;
                Double p2;
                try {
                    p1 = Double.parseDouble(station.getReg_price());
                } catch (NumberFormatException e) {
                    p1 = 0.0;
                }
                try {
                    p2 = Double.parseDouble(t1.getReg_price());
                } catch (NumberFormatException e) {
                    p2 = 0.0;
                }

                if (p1 < p2) {
                    return -1;
                } else if (p1 > p2) {
                    return 1;
                }
                return 0;
            } else if (fuelType.toLowerCase().equals("plus")) {
                Double p1;
                Double p2;
                try {
                    p1 = Double.parseDouble(station.getMid_price());
                } catch (NumberFormatException e) {
                    p1 = 0.0;
                }
                try {
                    p2 = Double.parseDouble(t1.getMid_price());
                } catch (NumberFormatException e) {
                    p2 = 0.0;
                }

                if (p1 < p2) {
                    return -1;
                } else if (p1 > p2) {
                    return 1;
                }
                return 0;
            } else if (fuelType.toLowerCase().equals("premium")) {
                Double p1;
                Double p2;
                try {
                    p1 = Double.parseDouble(station.getPrem_price());
                } catch (NumberFormatException e) {
                    p1 = 0.0;
                }

                try {
                    p2 = Double.parseDouble(t1.getPrem_price());
                } catch (NumberFormatException e) {
                    p2 = 0.0;
                }
                if (p1 < p2) {
                    return -1;
                } else if (p1 > p2) {
                    return 1;
                }
                return 0;
            } else { //diesel
                Double p1;
                Double p2;
                try {
                    p1 = Double.parseDouble(station.getDiesel_price());
                } catch (NumberFormatException e) {
                    p1 = 0.0;
                }
                try {
                    p2 = Double.parseDouble(t1.getDiesel_price());
                } catch (NumberFormatException e) {
                    p2 = 0.0;
                }
                if (p1 < p2) {
                    return -1;
                } else if (p1 > p2) {
                    return 1;
                }
                return 0;
            }
        }
    }
}
