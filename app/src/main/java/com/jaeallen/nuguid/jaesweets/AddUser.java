package com.jaeallen.nuguid.jaesweets;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddUser extends AppCompatActivity {


    databaseHelper dbHelper = new databaseHelper(this);

    RadioButton admin;
    EditText user, pass1, pass2;
    String a,b,c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);


        admin = (RadioButton) findViewById(R.id.admin);
        pass1 = (EditText) findViewById(R.id.passBox);
        pass2 = (EditText) findViewById(R.id.passBox2);
        user = (EditText) findViewById(R.id.prodBox);
    }

    public void ManageUsers(View v){
        Intent i = new Intent(getApplicationContext(), ManageUsers.class);
        startActivity(i);

    }

    public void addUser(View v){

        a = user.getText().toString();
        b = pass1.getText().toString();
        c = pass2.getText().toString();
        if(a.length()< 4){
            msg("Username is too short!\n\n-JaeSweets");
            user.setText("");
            pass1.setText("");
            pass2.setText("");
        }else if(!b.equals(c)){
            msg("Password does not match!\n\n-JaeSweets");
            user.setText("");

            pass1.setText("");
            pass2.setText("");
        }else if(b.equals(c)&& b.length() < 4){
            msg("Password is too short!\n\n-JaeSweets");
            user.setText("");

            pass1.setText("");
            pass2.setText("");
        }else{

            if(dbHelper.doesExist(a)){
                int x= 0;
                if(admin.isChecked()) x =1;

                dbHelper.updateAccount(a,b,x);
                msg(a+"'s account details are successfully updated!\n\n-JaeSweets");
                user.setText("");
                pass1.setText("");
                pass2.setText("");
            }else {
                Account acc = new Account();

                if(admin.isChecked())
                acc.setAdmin(1);
                else
                    acc.setAdmin(0);

                acc.setUsername(a);
                acc.setPassword(b);

                dbHelper.addAccount(acc);

                user.setText("");
                pass1.setText("");
                pass2.setText("");
                msg("Successfully Registered!\n\n-JaeSweets");
            }
        }
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
