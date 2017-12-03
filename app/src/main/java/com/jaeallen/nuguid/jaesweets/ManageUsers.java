package com.jaeallen.nuguid.jaesweets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ManageUsers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);
    }


    public void AddNewUser(View v){
        Intent i = new Intent(getApplicationContext(), AddUser.class);
        startActivity(i);
    }

    public void deleteUser(View v){
        Intent i = new Intent(getApplicationContext(), DeleteAUser.class);
        startActivity(i);
    }
    public void viewUsers(View v){
        Intent i = new Intent(getApplicationContext(), ViewUsers.class);
        startActivity(i);
    }

    public void Return(View v){
        Intent i = new Intent(getApplicationContext(), Admin_Choose.class);
        startActivity(i);
        finish();
    }
}
