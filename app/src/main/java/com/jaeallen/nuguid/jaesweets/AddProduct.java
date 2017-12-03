package com.jaeallen.nuguid.jaesweets;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddProduct extends AppCompatActivity {

    EditText idBox, nameBox, priceBox, qtyBox;
    String b;
    int a, d;
    double c;

    databaseHelper2 dbHelper = new databaseHelper2(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        idBox = (EditText) findViewById(R.id.pId);
        nameBox = (EditText) findViewById(R.id.pName);
        priceBox = (EditText) findViewById(R.id.pPrice);
        qtyBox = (EditText) findViewById(R.id.pQty);
    }



    public void ManageProducts(View v){
        Intent i = new Intent(getApplicationContext(), ManageProduct.class);
        startActivity(i);
    }


    public void addProduct(View v){

        try {
            a = Integer.parseInt((idBox.getText().toString()));
            b = nameBox.getText().toString();
            c = Double.parseDouble((priceBox.getText().toString()));
            d = Integer.parseInt((qtyBox.getText().toString()));
        }catch (Exception e){
            msg("Invalid Data Types!\nOne or more inputs are invalid.\n\n-JaeSweets");
        }

        if(a < 3){
            msg("Product ID must be above 100 and greater than or equal to 3 digits!\n\n-JaeSweets");
            clear();
        }else if(b.length() < 3 ){
            msg("Product name is too short!\n\n-JaeSweets");
            clear();
        }else if(b.length() > 50){
            msg("Product name is too long!\n\n-JaeSweets");
            clear();
        }else if(c<=0 || d<=0){
            msg("Product price and quantity must be more than 0!\n\n-JaeSweets");
            clear();
        }else{

            if(dbHelper.doesExist(a)){
                int x= 0;
                Product prod = new Product();


                prod.setProductId(a);
                prod.setProductName(b);
                prod.setQuantity(d);
                prod.setPrice(c);
                dbHelper.updateProduct(prod);
                msg(b+"'s product details are successfully updated!\n\n-JaeSweets");

                clear();

            }else {
                Product prod = new Product();


                prod.setProductId(a);
                prod.setProductName(b);
                prod.setQuantity(d);
                prod.setPrice(c);

                dbHelper.addProduct(prod);

                clear();
                msg("Product Successfully Added!\n\n-JaeSweets");
            }
        }
    }


    public void clear() {
        idBox.setText("");
        nameBox.setText("");
        priceBox.setText("");
        qtyBox.setText("");
    }
    //message method
    public void msg(String msg){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Okay",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();

                    }
                });



        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
