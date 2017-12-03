package com.jaeallen.nuguid.jaesweets;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText user, pass;
    String a,b;

    private SharedPreferences sharedPref;
    databaseHelper dbHelper = new databaseHelper(this);
    databaseHelper2 dbHelper2 = new databaseHelper2(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Activity activity = MainActivity.this;

        sharedPref = PreferenceManager.getDefaultSharedPreferences(activity);

        if (sharedPref.getBoolean("my_first_time", true)) {
            createProducts();
            dbHelper.makeAdmin();
            sharedPref.edit().putBoolean("my_first_time", false).commit();
        }


        user = (EditText) findViewById(R.id.prodBox);
        pass = (EditText) findViewById(R.id.passBox);

    }

    public void createProducts(){
        msg("Created default products!\n\n-JaeSweets");

        int[] id={101,102,103,104,105,106,107,108,109,110};
        String[] name={"Lollipop","Soda Candy","Chocolate Candy","Popsicle","Milo Candy","Jellyfish Candy","Rock Salt Candy","Ice Cream","Pineapple Soda","Jae's Special"};
        double[] price={1.2,4.2,2.0,5.2,3.2,4.2,4.6,9.2,12.3,999.999};
        int[] qty={50,50,50,50,50,50,50,50,50,9999};

        for(int x=0;x<10;x++){
            Product prod = new Product();
            prod.setProductId(id[x]);
            prod.setProductName(name[x]);
            prod.setQuantity(qty[x]);
            prod.setPrice(price[x]);
            dbHelper2.addProduct(prod);
        }
    }

    public void Login(View v){
        a = user.getText().toString();
        b = pass.getText().toString();
        if(a.length() < 4){
            msg("Username is too short!\n\n-JaeSweets");
            user.setText("");
            pass.setText("");
        }else if(b.length()< 4){
            msg("Password is too short!\n\n-JaeSweets");
            user.setText("");
            pass.setText("");
        }else{
        String password = dbHelper.searchPass(a);

        if(password.equals(b)){

            if(dbHelper.isAdmin(a)){
                Intent i = new Intent(getApplicationContext(), Admin_Choose.class);
                startActivity(i);
                 finish();
            }else{
                Intent i = new Intent(getApplicationContext(), BuyProduct.class);
                startActivity(i);
                finish();
            }

        }else{
            msg("Incorrect or Invalid Account!\n\n-JaeSweets");
            user.setText("");
            pass.setText("");
        }


        }
    }

    public void Register(View v){
        Intent i = new Intent(getApplicationContext(), RegisterNewUser.class);
        startActivity(i);
        finish();
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
