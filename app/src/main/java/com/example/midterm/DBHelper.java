package com.example.midterm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "FruitRecord.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table FruitRecorddetails(id TEXT primary key, name TEXT, dsc TEXT, price TEXT, qty TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists FruitRecorddetails");
        onCreate(DB);
    }
    public Boolean insertuserdata(String id, String name, String dsc, String price, String qty)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("dsc", dsc);
        contentValues.put("price", price);
        contentValues.put("qty", qty);
        long result=DB.insert("FruitRecorddetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updateuserdata(String id, String name, String dsc, String price, String qty)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("dsc", dsc);
        contentValues.put("price", price);
        contentValues.put("qty", qty);
        Cursor cursor = DB.rawQuery("Select * from FruitRecorddetails where id = ?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.update("FruitRecorddetails", contentValues, "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Boolean deletedata (String id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from FruitRecorddetails where id = ?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.delete("FruitRecorddetails", "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from FruitRecorddetails", null);
        return cursor;
    }
}
