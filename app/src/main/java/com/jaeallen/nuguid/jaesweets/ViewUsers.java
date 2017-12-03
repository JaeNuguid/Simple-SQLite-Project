package com.jaeallen.nuguid.jaesweets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ViewUsers extends AppCompatActivity {

    private ListView listView;
    private ListViewAdapter2 adapter;
    private List<Account> accList;
    private TextView title;

    databaseHelper databaseHelper = new databaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);
        listView = (ListView) findViewById(R.id.list_view);
        title = (TextView)findViewById(R.id.total);

        reloadingDatabase();
    }

    public void reloadingDatabase() {
        accList = databaseHelper.getAllAccounts();
        if (accList.size() == 0) {
            Toast.makeText(this, "No record found in database!", Toast.LENGTH_SHORT).show();
            title.setVisibility(View.GONE);
        }
        adapter = new ListViewAdapter2(this, R.layout.item_listview, accList, databaseHelper);
        listView.setAdapter(adapter);
        title.setVisibility(View.VISIBLE);
        title.setText("Number of Accounts: " + databaseHelper.getAccountsCount());
    }

    public void ManageUsers(View v){
        Intent i = new Intent(getApplicationContext(), ManageUsers.class);
        startActivity(i);
    }
}
