package com.jaeallen.nuguid.jaesweets;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DeleteProduct extends AppCompatActivity {



    EditText prod;


    databaseHelper2 dbHelper = new databaseHelper2(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product);
        prod = (EditText) findViewById(R.id.prodBox);
    }



    public void ManageProducts(View v){
        Intent i = new Intent(getApplicationContext(), ManageProduct.class);
        startActivity(i);
    }

    public void deleteProduct(View v){

        int jae = Integer.parseInt(prod.getText().toString());

        if(dbHelper.doesExist(jae)){

                dbHelper.deleteProduct(jae);

                msg("Product is successfully deleted!");

        }else{
            msg("Product with ID "+jae+" does not exist!");

        }

        prod.setText("");

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
