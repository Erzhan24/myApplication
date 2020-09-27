package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
/**
 * This app displays an order form to order coffee.
 */
public class MainActivity<savedInstanceState> extends AppCompatActivity {
    int numberOfCoffees=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        if (numberOfCoffees==100){
            Toast.makeText(this, "You cannot have more than 100 coffeees", Toast.LENGTH_SHORT).show();
            return;
        }
        numberOfCoffees=numberOfCoffees+1;
        display(numberOfCoffees);
        }
    /**
     * This method is called when the order button is clicked.
     */
    public void decrement(View view) {
        Toast.makeText(this, "You cannot have more than 1 coffeees", Toast.LENGTH_SHORT).show();
        if (numberOfCoffees==1){
            return;
        }
        numberOfCoffees=numberOfCoffees-1;
        display(numberOfCoffees);

        }
    public void submitOrder(View view ) {
       CheckBox whippeCreamCheckBox=(CheckBox) findViewById(R.id.whipped_cream_checkbox);
      boolean hasWhippedCream=whippeCreamCheckBox.isChecked();
        CheckBox toppingCheckBox=(CheckBox) findViewById(R.id.topping_checkbox);
        boolean hastopping=toppingCheckBox.isChecked();
        int price=calculatePrice(hasWhippedCream, hastopping);


      EditText text =(EditText)findViewById(R.id.name_field);
       String value =text.getText().toString();
       String priceMessage=createOrderSummary(value,price, hasWhippedCream,hastopping);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"just java order for"+ value);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    /*
    calculates the price of the order
     */
    private int calculatePrice(boolean addWhippedCream, boolean addtopping){
        int baseprice=5;
        if (addWhippedCream){
            baseprice=baseprice+1;
        }
        if (addtopping){
            baseprice=baseprice+2;
        }
        return numberOfCoffees*baseprice;




    }
    private String createOrderSummary( String name,int price, boolean addWhippedCream, boolean addtopping){
        String priceMessage="Name: "+name;
        priceMessage+="\nAdd whipped cream? "+addWhippedCream;
        priceMessage+="\nAdd chocolate? "+addtopping;
        priceMessage+="\nQuantity: "+numberOfCoffees;
        priceMessage+="\nTotal: $"+price;
        priceMessage+="\n"+getString(R.string.thank_you);
        return priceMessage;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    public void onCheckboxClicked(View view) {
    }
    public void CheckboxClicked(View view) {
    }
}