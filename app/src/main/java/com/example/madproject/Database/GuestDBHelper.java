package com.example.madproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GuestDBHelper extends SQLiteOpenHelper {
    public GuestDBHelper(Context context) {
        super(context, "Guestrecord.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Guestdetail(Name TEXT,Email TEXT primary key,phone TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Guestdetail");
    }
    public boolean insertguestdata(String name,String Email,String Password,String Phone){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name",name);
        contentValues.put("Email",Email);
        contentValues.put("password",Password);
        contentValues.put("phone",Phone);
        Long result = DB.insert("Guestdetail",null,contentValues);
        if (result ==-1){
            return false;
        }
        else
        {
            return  true;
        }

    }
}
