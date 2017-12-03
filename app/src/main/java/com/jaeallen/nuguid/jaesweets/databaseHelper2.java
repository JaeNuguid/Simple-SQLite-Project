package com.jaeallen.nuguid.jaesweets;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jae on 12/2/2017.
 */

public class databaseHelper2 extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =1;

    private static final String TABLE_NAME = "products";
    private static final String DATABASE_NAME = "products.db";
    private static final String COLUMN_PRICE = "prodprice";
    private static final String COLUMN_QUANTITY = "prodqty";
    private static final String COLUMN_NAME= "prodname";
    private static final String COLUMN_ID= "prodid";
    SQLiteDatabase db;

    private static String TABLE_CREATE = "create table "+TABLE_NAME+" ("+COLUMN_ID+" int not null,"
            +COLUMN_NAME+" text not null,"+COLUMN_PRICE+" double not null,"+COLUMN_QUANTITY+" int not null);";

    public databaseHelper2(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void addProduct(Product prod){
        db  = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, prod.getProductId());
        values.put(COLUMN_NAME, prod.getProductName());
        values.put(COLUMN_PRICE, prod.getPrice());
        values.put(COLUMN_QUANTITY, prod.getQuantity());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public void deleteProduct(int productId){

        db  = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "='" + productId+"'", null) ;
        db.close();
    }

    public int getProductsCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor dataCount = db.rawQuery("select " + COLUMN_ID + " from " + TABLE_NAME, null);

        int count = dataCount.getCount();
        dataCount.close();
        db.close();

        return count;
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();

        // select query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all table records and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setProductId(cursor.getInt(0));
                product.setProductName(cursor.getString(1));
                product.setPrice(cursor.getDouble(2));
                product.setQuantity(cursor.getInt(3));


                // Adding friend to list
                productList.add(product);
            } while (cursor.moveToNext());
        }

        return productList;
    }

    public Product getProduct(int id){

        db = this.getReadableDatabase();
        String query= "select * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        Product prod = new Product();
        int a;
        if(cursor.moveToFirst()){
            do{
                a = cursor.getInt(0);

                if(a == id){

                    prod.setProductId(cursor.getInt(0));
                    prod.setProductName(cursor.getString(1));
                    prod.setPrice(cursor.getDouble(2));
                    prod.setQuantity(cursor.getInt(3));
                    return prod;

                }
            }while(cursor.moveToNext());
        }

        prod= null;
        return prod;
    }

    public int updateProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, product.getProductId());
        values.put(COLUMN_NAME, product.getProductName());
        values.put(COLUMN_PRICE, product.getPrice());
        values.put(COLUMN_QUANTITY, product.getQuantity());

        // updating row
        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(product.getProductId())});
    }

    public void updatePrdouct(int prodId, String newName, double newPrice, int newQty){

        ContentValues data=new ContentValues();

        if(newName != null)
        data.put(COLUMN_NAME, newName);
        if(newPrice != 0)
        data.put(COLUMN_PRICE, newPrice);
        if(newQty != 0)
        data.put(COLUMN_QUANTITY, newQty);


        db.update(TABLE_NAME, data, COLUMN_ID+"='"+prodId+"'",null);
        db.close();
    }
    public void deleteProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(product.getProductId())});
        db.close();
    }

    public boolean doesExist(int prodId){

        db = this.getReadableDatabase();
        String query= "select * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        int a;
        if(cursor.moveToFirst()){
            do{
                a = cursor.getInt(0);

                if(a == (prodId)){

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
