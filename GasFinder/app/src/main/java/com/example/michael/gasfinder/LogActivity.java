package com.example.michael.gasfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class LogActivity extends AppCompatActivity implements View.OnClickListener
{
    RadioButton regular;
    RadioButton plus;
    RadioButton premium;
    RadioButton diesel;

    //Buttons
    Button submitButton;

    //EditTexts
    EditText totalInput;

    RadioGroup radioGroup;

    String station_name;
    String station_address;
    Double regular_price;
    Double premium_price;
    Double plus_price;
    Double diesel_price;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        station_name = getIntent().getStringExtra("Station_Name");
        station_address = getIntent().getStringExtra("Station_Address");
        regular_price = getIntent().getDoubleExtra("Regular_Price", 0);
        premium_price = getIntent().getDoubleExtra("Premium_Price", 0);
        plus_price = getIntent().getDoubleExtra("Plus_Price", 0);
        diesel_price = getIntent().getDoubleExtra("Diesel_Price", 0);


        //EditTexts
        totalInput = findViewById(R.id.totalInput);
        //Buttons
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
        }
    }

    @Override
    public void onClick(View view)
    {
        radioGroup = findViewById(R.id.radioGroup);

        int selectedID = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = (RadioButton)findViewById(selectedID);

        //read fuel type
        String fuelType = selectedRadioButton.getText().toString();
        Double gasPrice = 0.0;

        //check radio button
        if (selectedRadioButton.getText().toString().equalsIgnoreCase("Regular"))
        {
            gasPrice = regular_price;
        } else if (selectedRadioButton.getText().toString().equalsIgnoreCase("Plus"))
        {
            gasPrice = plus_price;
        } else if (selectedRadioButton.getText().toString().equalsIgnoreCase("Premium"))
        {
            gasPrice = premium_price;
        } else //Diesel
        {
            gasPrice = diesel_price;
        }

        Intent intent = new Intent(this, HistoryActivity.class);

        //pass to history activity
        intent.putExtra("Station_Name", station_name);
        intent.putExtra("Station_Address", station_address);
        intent.putExtra("Fuel_Type", fuelType);
        intent.putExtra("Fuel_Price", gasPrice);
        intent.putExtra("Total_Price", Double.parseDouble(totalInput.getText().toString()));
        startActivity(intent);


        //Do whatever you want that should happen when the submit button is clicked
    }
}
