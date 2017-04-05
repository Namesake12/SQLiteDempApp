package com.sqlitedemo.sqlitedempapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Neeraj on 5/21/2016.
 */
public class DatabaseHelperClass extends SQLiteOpenHelper {
    private final String TABLE_NAME = "users";
    public DatabaseHelperClass(Context context) {
        super(context, "login_details", null, 1);
        SQLiteDatabase db = getWritableDatabase();
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS users (" +
                "name TEXT," +
                "password TEXT)");
    }

    public void addUser(String name, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("password",password);
        db.insert("users",null,values);
    }

    public String getUsers(String name){
        SQLiteDatabase db = getReadableDatabase();
        String []columns = {"name","password"};
        Cursor c;
        if(name != ""){
            c = db.query("users", columns,"name LIKE '%" + name + "%'",null,null,null,null);
        }
        else{
            c = db.query("users", columns,null,null,null,null,null);
        }
        if(c.getCount() > 0){
            c.moveToFirst();
            return c.getString(c.getColumnIndex("password"));
        }
        return "No User";
    }

    public void updateUser(String name, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", password);
        db.update("users",values,"name = '" + name + "'",null);
    }

    public void deleteUser(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("users","name = '" + name +"'",null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
