package com.jaeallen.nuguid.jaesweets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BuyProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_product);
    }

    public void buyNow(View v){

    }

    public void clear(View v){


    }
    public void Logout(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
