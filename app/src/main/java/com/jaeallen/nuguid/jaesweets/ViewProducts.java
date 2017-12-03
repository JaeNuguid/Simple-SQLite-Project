package com.jaeallen.nuguid.jaesweets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ViewProducts extends AppCompatActivity {

    private ListView listView;
    private ListViewAdapter adapter;
    private List<Product> productList;
    private TextView title;

    databaseHelper2 databaseHelper = new databaseHelper2(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);

        listView = (ListView) findViewById(R.id.list_view);
        title = (TextView)findViewById(R.id.total);

        reloadingDatabase();
    }

    public void reloadingDatabase() {
        productList = databaseHelper.getAllProducts();
        if (productList.size() == 0) {
            Toast.makeText(this, "No record found in database!", Toast.LENGTH_SHORT).show();
            title.setVisibility(View.GONE);
        }
        adapter = new ListViewAdapter(this, R.layout.item_listview, productList, databaseHelper);
        listView.setAdapter(adapter);
        title.setVisibility(View.VISIBLE);
        title.setText("Number of Products: " + databaseHelper.getProductsCount());
    }

    public void ManageProducts(View v){
        Intent i = new Intent(getApplicationContext(), ManageProduct.class);
        startActivity(i);
    }
}
