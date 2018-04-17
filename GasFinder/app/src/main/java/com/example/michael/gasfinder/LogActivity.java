package com.example.michael.gasfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LogActivity extends AppCompatActivity implements View.OnClickListener
{
    //Checkboxes
    CheckBox regularCB;
    CheckBox plusCB;
    CheckBox premiumCB;
    CheckBox dieselCB;

    //Buttons
    Button submitButton;

    //EditTexts
    EditText totalInput;
    EditText perGalInput;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        //Checkboxes
        regularCB = findViewById(R.id.regularCB);
        plusCB = findViewById(R.id.plusCB);
        premiumCB = findViewById(R.id.premiumCB);
        dieselCB = findViewById(R.id.dieselCB);

        //EditTexts
        totalInput = findViewById(R.id.totalInput);
        perGalInput = findViewById(R.id.perGalInput);

        //Buttons
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.regularCB:
                if (checked)
                {

                }
                else
                {

                }
                break;
            case R.id.plusCB:
                if (checked)
                {

                }
                else
                {

                }
                break;
            case R.id.premiumCB:
                if (checked)
                {

                }
                else
                {

                }
                break;
            case R.id.dieselCB:
                if (checked)
                {

                }
                else
                {

                }
                break;
        }
    }

    @Override
    public void onClick(View view)
    {
        //Do whatever you want that should happen when the submit button is clicked
    }
}
