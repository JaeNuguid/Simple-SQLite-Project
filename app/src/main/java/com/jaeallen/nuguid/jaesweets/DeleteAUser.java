package com.jaeallen.nuguid.jaesweets;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DeleteAUser extends AppCompatActivity {

    EditText user;

    databaseHelper dbHelper = new databaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_auser);
        user = (EditText) findViewById(R.id.prodBox);
    }

    public void ManageUsers(View v){
        Intent i = new Intent(getApplicationContext(), ManageUsers.class);
        startActivity(i);
    }

    public void deleteUser(View V){

        String jae = user.getText().toString();

        if(dbHelper.doesExist(jae)){
            if(jae.equals("admin")){
                msg("Admin account cannot be deleted!\n\n-JaeSweets");
            }else {
                dbHelper.deleteUser(jae);

                msg(jae + "'s Account is successfully deleted!");
            }
        }else{
            msg("User Account does not exist!");
        }

        user.setText("");

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
