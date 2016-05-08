package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        if (quantity == 5)
        {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1)
        {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    public void submitOrder(View view) {
        int numberOfCoffees = 2;
        int basePrice = 5;
        int price;
        String priceMessage;
display(numberOfCoffees);

        boolean addWhippedCream;
        boolean addChocolate;
        String name;
        int total_base_price = basePrice;

        EditText editText = (EditText) findViewById(R.id.id_name);
        name = editText.getText().toString();

        CheckBox checkbox = (CheckBox) findViewById(R.id.id_whippedCream);
        addWhippedCream = checkbox.isChecked();
        Log.v("MainActivity","Checkbox Status"+addWhippedCream);

        checkbox = (CheckBox) findViewById(R.id.id_chocolate);
        addChocolate = checkbox.isChecked();

        if (addWhippedCream)
            total_base_price = total_base_price + 1;
        if (addChocolate)
            total_base_price = total_base_price +2;


        price = quantity * (total_base_price);

        if(quantity != 0) {
            Log.v("MainActivity","The price is: "+price);
            String msg = "Name: "+name;
            priceMessage = msg+"\nPrice: $" + price +"\nHas Whipped Cream added? "+addWhippedCream +"\nChocolate? "+addChocolate+"\nThank You!";
        }
        else {
            Log.v("MainActivity","The price is: "+price);
            priceMessage = "Price: $" + price;
        }
//        displayMessage(priceMessage);

        String addresses[] = {"sandesh.bhujbal28@gmail.com"};
        String subject = "Just Java Order for "+name;


        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setType("*/*");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);

        /*intent.putExtra(Intent.EXTRA_STREAM, attachment);*/
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView OrdersummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        OrdersummaryTextView.setText(message);
    }
}