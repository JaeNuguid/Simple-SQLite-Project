package com.jaeallen.nuguid.jaesweets;

/**
 * Created by Jae on 12/3/2017.
 */
import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListViewAdapter2 extends ArrayAdapter<Account> {

    private ViewUsers activity;
    private databaseHelper databaseHelper;
    private List<Account> accList;

    public ListViewAdapter2(ViewUsers context, int resource, List<Account> objects, databaseHelper helper) {
        super(context, resource, objects);
        this.activity = context;
        this.databaseHelper = helper;
        this.accList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_listview, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(getItem(position).getUsername());

        //Delete an item
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.deleteUser(getItem(position)); //delete in db
                Toast.makeText(activity, "Account Deleted!", Toast.LENGTH_SHORT).show();

                //reload the database to view
                activity.reloadingDatabase();
            }
        });



        //show details when each row item clicked
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
                alertDialog.setTitle("Account Details");

                LinearLayout layout = new LinearLayout(activity);
                layout.setPadding(10, 10, 10, 10);
                layout.setOrientation(LinearLayout.VERTICAL);


                TextView user = new TextView(activity);
                layout.addView(user);
                TextView admin = new TextView(activity);
                layout.addView(admin);


                user.setText("Username: " + getItem(position).getUsername());
                String x = "Regular User";
                if( getItem(position).getAdmin()> 0) x = "Administrator";
                admin.setText("Account Type: " + x);

                alertDialog.setView(layout);
                alertDialog.setNegativeButton("OK", null);

                //show alert
                alertDialog.show();
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        private TextView name;
        private View btnDelete;

        public ViewHolder (View v) {
            name = (TextView)v.findViewById(R.id.item_name);
            btnDelete = v.findViewById(R.id.delete);
        }
    }
}