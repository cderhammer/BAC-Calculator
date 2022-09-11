
/*
    In Class 02

    8/29/2022

    Group 30
    Matthew Carroll
    Conor Derhammer
 */

package com.example.group30_inclass02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "calculator";

    EditText editTicketPrice;
    TextView labelDiscountPercentValue;
    TextView labelDiscountPriceValue;

    Button buttonDiscount1;
    Button buttonDiscount2;
    Button buttonDiscount3;
    Button buttonDiscount4;
    Button buttonDiscount5;
    Button buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Grab edit and labels
        editTicketPrice = findViewById(R.id.editTicketPrice);
        labelDiscountPercentValue = findViewById(R.id.labelDiscountPercentValue);
        labelDiscountPriceValue = findViewById(R.id.labelDiscountPriceValue);

        // Grab button
        buttonDiscount1 = findViewById(R.id.buttonDiscount1);
        buttonDiscount2 = findViewById(R.id.buttonDiscount2);
        buttonDiscount3 = findViewById(R.id.buttonDiscount3);
        buttonDiscount4 = findViewById(R.id.buttonDiscount4);
        buttonDiscount5 = findViewById(R.id.buttonDiscount5);
        buttonClear = findViewById(R.id.buttonClear);

        /*
            Give each discount button the same listener
            Give the clear button its own listener
         */
        buttonDiscount1.setOnClickListener(new DiscountButtonClick());
        buttonDiscount2.setOnClickListener(new DiscountButtonClick());
        buttonDiscount3.setOnClickListener(new DiscountButtonClick());
        buttonDiscount4.setOnClickListener(new DiscountButtonClick());
        buttonDiscount5.setOnClickListener(new DiscountButtonClick());
        buttonClear.setOnClickListener(new ClearButtonClicked());
    }

    public double getPriceEntered(){
        // Grab price from text input by user
        String priceText = editTicketPrice.getText().toString();
        double price;

        // Check if text is a number
        try {
            price = Double.parseDouble(priceText);

            // Error if price is negative
            if (price < 0)
                return -1;

            return price;

        // Error if price is not a number
        } catch(NumberFormatException e){
            return -1;
        }
    }

    public class DiscountButtonClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            // Grab price entered by user
            double price = getPriceEntered();

            // If the above returns -1, theres an error
            // Otherwise, continue as normal
            if (price != -1) {
                // Grab id to check which discount button was pressed
                int viewId = view.getId();
                Log.d(TAG, "" + viewId);

                // Store discount percent for later modification
                double discountPercent;

                // Find which button was pressed
                // Set discount percent
                if (viewId == buttonDiscount1.getId())
                    discountPercent = 0.05;
                else if (viewId == buttonDiscount2.getId())
                    discountPercent = 0.10;
                else if (viewId == buttonDiscount3.getId())
                    discountPercent = 0.15;
                else if (viewId == buttonDiscount4.getId())
                    discountPercent = 0.20;
                else
                    discountPercent = 0.50;

                // Calculate discounted price
                double discountedPrice = price - (price * discountPercent);

                // Set labels to show discount percent and price
                String percentText = (int)(discountPercent * 100) + "%";
                String priceText = String.valueOf(Math.floor(discountedPrice * 100.0) / 100.0);

                labelDiscountPercentValue.setText(percentText);
                labelDiscountPriceValue.setText(priceText);

            } else {
                // Send toast to user if the input is incorrect
                Toast.makeText(getApplicationContext(), "Input a positive number as the price", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class ClearButtonClicked implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            // Clear current input and displays
            editTicketPrice.setText("");
            labelDiscountPercentValue.setText("");
            labelDiscountPriceValue.setText("");

        }
    }
}