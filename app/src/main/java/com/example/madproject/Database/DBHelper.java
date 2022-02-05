package com.example.madproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.madproject.model.HallOwnerLogininfo;
import com.example.madproject.model.Hallinfo;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context,"Record.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Hallownerdetail(name TEXT  ,Email TEXT primary key,phone TEXT,password TEXT)");
       db.execSQL("Create Table Halldata(hallownername TEXT ,Hallname TEXT primary key , Hallcity TEXT" +
               ",Halllocation TEXT,Hallcapacity TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Hallownerdetails");
    db.execSQL("Drop Table if exists Halldata");


    }
    public boolean inserthalldata(Hallinfo hall){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hallownername",hall.getHallownername());
        contentValues.put("Hallname",hall.getHallname());
        contentValues.put("Hallcity",hall.getHallcity());
        contentValues.put("Halllocation",hall.getHalllocation());
        contentValues.put("Hallcapacity",hall.getHallcapacity());
        Long result = DB.insert("Halldata",null,contentValues);
        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean inserthallownerdata(String name,String email,String Password,String Phone){

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name ",name);
        contentValues.put("Email ",email);
        contentValues.put("Password ",Password);
        contentValues.put("Phone ",Phone);
        Long result = DB.insert("Hallownerdetail",null,contentValues);
        if(result == -1){
            return  false;
        }
        else{
            return  true;
        }

    }

    public boolean updatehallownerdata(String name,String email,String Password,String Phone){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name ",name);
        contentValues.put("Password ",Password);
        contentValues.put("Phone ",Phone);
        Cursor cursor = DB.rawQuery("Select * from Hallownerdetail where Email = ?",new String[] {email});
        if(cursor.getCount()>0){
            Long result = Long.valueOf(DB.update("Hallownerdetail",contentValues,"Email=?",new String[] {email}));
            if(result == -1){
                return  false;
            }
            else{
                return  true;
            }}
        else{
            return  false;
        }}
    public boolean deletehallownerdata(String email){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = DB.rawQuery("Select * from Hallownerdetail where name = ?",new String[] {email});
        if(cursor.getCount()>0){
            long result = DB.delete("Hallownerdetail","Email=?",new String[] {email});
            if(result == -1){
                return  false;
            }
            else{
                return  true;
            }}
        else{
            return  false;
        }}
    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = DB.rawQuery("Select * from Hallownerdetail ",null);
        return cursor;
    }
    public List<HallOwnerLogininfo> getsingledata(String email){
        List<HallOwnerLogininfo> data = new ArrayList<>();
        SQLiteDatabase DB = this.getReadableDatabase();

        String SQL = "Select * from Hallownerdetail where Email = " ;
        //Log.d(LOG, SQL);

        //Cursor c = DB.rawQuery(SQL, null);
        Cursor c = DB.query("Hallownerdetail",new String[]{"name","Email","Password","Phone"},"email = ?",new String[]{email} ,null,null,null);

        c.moveToFirst();


        //
        for(int i = 0 ; i < c.getCount() ; i++) {
            String n = c.getString(0);
            String E = c.getString(1);
            String pass = c.getString(2);
            String phone = c.getString(3);
            //HallOwnerLogininfo hallinfo = new HallOwnerLogininfo(n, E,pass,phone);
            HallOwnerLogininfo temp = new HallOwnerLogininfo(n, E,pass,phone);
            data.add(temp);
            // hallinfo.setName(n);
            //h/allinfo.setEmail(E);
            //hallinfo.setPass(p);
            //hallinfo.setPhone(Phone);
            c.moveToNext();
        }

        return data;


    }


}
