package com.jaeallen.nuguid.jaesweets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ManageProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product);
    }


    public void Admin_Choose(View v){
        Intent i = new Intent(getApplicationContext(), Admin_Choose.class);
        startActivity(i);
        finish();
    }

    public void AddProduct(View v){
        Intent i = new Intent(getApplicationContext(), AddProduct.class);
        startActivity(i);

    }

    public void DeleteProduct(View v){
        Intent i = new Intent(getApplicationContext(), DeleteProduct.class);
        startActivity(i);

    }


    public void ViewProducts(View v){
        Intent i = new Intent(getApplicationContext(), ViewProducts.class);
        startActivity(i);

}
}
