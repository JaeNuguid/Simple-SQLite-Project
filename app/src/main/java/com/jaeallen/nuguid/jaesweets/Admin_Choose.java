package com.jaeallen.nuguid.jaesweets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Admin_Choose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__choose);
    }


    public void Logout(View v){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }



    public void ManageProductss(View v){
        Intent i = new Intent(getApplicationContext(), ManageProduct.class);
        startActivity(i);

    }

    public void ManageUsers(View v){
        Intent i = new Intent(getApplicationContext(), ManageUsers.class);
        startActivity(i);

    }
}
