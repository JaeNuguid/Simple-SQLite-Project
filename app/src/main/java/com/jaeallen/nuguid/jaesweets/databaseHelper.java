package com.jaeallen.nuguid.jaesweets;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jae on 12/2/2017.
 */

public class databaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =1;

    private static final String TABLE_NAME = "accounts";
    private static final String DATABASE_NAME = "accounts.db";
    private static final String COLUMN_USERNAME = "uname";
    private static final String COLUMN_PASSWORD = "pass";
    private static final String COLUMN_ADMIN= "admin";
    SQLiteDatabase db;
    private static String TABLE_CREATE = "create table "+TABLE_NAME+" ("+COLUMN_USERNAME+" text not null,"
            +COLUMN_PASSWORD+" text not null,"+COLUMN_ADMIN+" int not null);";

    public databaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void addAccount(Account acc){
        db  = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,acc.getUsername());
        values.put(COLUMN_PASSWORD,acc.getPassword());
        values.put(COLUMN_ADMIN, acc.getPassword());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public void deleteUser(String user){

        db  = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_USERNAME + "='" + user+"'", null) ;
        db.close();
    }
    public void deleteUser(Account acc) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_USERNAME + " = ?", new String[]{String.valueOf(acc.getUsername())});
        db.close();
    }
    public List<Account> getAllAccounts() {
        List<Account> accList = new ArrayList<>();

        // select query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all table records and adding to list
        if (cursor.moveToFirst()) {
            do {
                Account acc = new Account();
                acc.setUsername(cursor.getString(0));
                acc.setPassword(cursor.getString(1));
                acc.setAdmin(cursor.getInt(2));


                // Adding friend to list
                accList.add(acc);
            } while (cursor.moveToNext());
        }

        return accList;
    }
    public String searchPass(String user){
            String password="";
            db = this.getReadableDatabase();
        String query= "select * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

    String a,b ="Not Found";
        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);

                if(a.equals(user)){

                    b = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }

        return b;
    }
    public int getAccountsCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor dataCount = db.rawQuery("select " + COLUMN_USERNAME + " from " + TABLE_NAME, null);

        int count = dataCount.getCount();
        dataCount.close();
        db.close();

        return count;
    }

    public void updateAccount(String user, String newPass, int newUserType){

        ContentValues data=new ContentValues();
        data.put(COLUMN_PASSWORD,newPass);
        data.put(COLUMN_ADMIN, newUserType);
        db.update(TABLE_NAME, data, COLUMN_USERNAME+"='"+user+"'",null);
        db.close();
    }

    public void makeAdmin(){
        db  = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, "admin");
        values.put(COLUMN_PASSWORD, "admin");
        values.put(COLUMN_ADMIN, 1);
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public boolean isAdmin(String user){

        db = this.getReadableDatabase();
        String query= "select * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        String a;
        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);

                if(a.equals(user)){

                    if(cursor.getInt(2) > 0){
                        return true;
                    }else
                    return false;

                }
            }while(cursor.moveToNext());
        }

        return false;
    }


    public boolean doesExist(String user){

        db = this.getReadableDatabase();
        String query= "select * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        String a;
        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);

                if(a.equals(user)){

                    return true;

                }
            }while(cursor.moveToNext());
        }

        return false;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query  = "DROP TABLE IF EXIST "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }


}
