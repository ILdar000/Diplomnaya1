package com.example.diplomnaya.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.diplomnaya.entity.History;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_REQUEST_HISTORY = "TABLE_REQUEST_HISTORY";
    private static final String KEY_RU_SHIRT = "KEY_RU_SHIRT";
    private static final String KEY_INT_SHIRT = "KEY_INT_SHIRT";
    private static final String KEY_EU_SHIRT = "KEY_EU_SHIRT";
    private static final String KEY_US_SHIRT = "KEY_US_SHIRT";
    private static final String KEY_RU_JEANS = "KEY_RU_JEANS";
    private static final String KEY_INT_JEANS = "KEY_INT_JEANS";
    private static final String KEY_EU_JEANS = "KEY_EU_JEANS";
    private static final String KEY_US_JEANS = "KEY_US_JEANS";
    private static final String KEY_HEIGHT = "KEY_HEIGHT";
    private static final String KEY_SEX = "KEY_SEX";

    public DBHelper(@Nullable Context context) {
        super(context, "dbv1.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_REQUEST_HISTORY + "("+
                KEY_RU_SHIRT +" text, "+
                KEY_INT_SHIRT +" text, "+
                KEY_EU_SHIRT +" text, "+
                KEY_US_SHIRT +" text, "+
                KEY_RU_JEANS +" text, "+
                KEY_INT_JEANS +" text, "+
                KEY_EU_JEANS +" text, "+
                KEY_US_JEANS +" text, "+
                KEY_HEIGHT +" text, "+
                KEY_SEX + " text "
                + ");"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REQUEST_HISTORY,null,null);
        db.close();
    }

    public void add(String RU_SHIRT, String INT_SHIRT,String EU_SHIRT, String US_SHIRT, String RU_JEANS, String INT_JEANS,String EU_JEANS, String US_JEANS, String HEIGHT, String SEX){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_RU_SHIRT,RU_SHIRT);
        cv.put(KEY_INT_SHIRT,INT_SHIRT);
        cv.put(KEY_EU_SHIRT,EU_SHIRT);
        cv.put(KEY_US_SHIRT,US_SHIRT);
        cv.put(KEY_RU_JEANS,RU_JEANS);
        cv.put(KEY_INT_JEANS,INT_JEANS);
        cv.put(KEY_EU_JEANS,EU_JEANS);
        cv.put(KEY_US_JEANS,US_JEANS);
        cv.put(KEY_HEIGHT,HEIGHT);
        cv.put(KEY_SEX,SEX);

        db.insert(TABLE_REQUEST_HISTORY,null,cv);
        db.close();
    }


    public List<History> getAll(){
        List<History> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_REQUEST_HISTORY,null,null,null,null,null,null);

        if (cursor.moveToFirst())
            do {

                int id_RS = cursor.getColumnIndex(KEY_RU_SHIRT);
                int id_IS = cursor.getColumnIndex(KEY_INT_SHIRT);
                int id_ES = cursor.getColumnIndex(KEY_EU_SHIRT);
                int id_US = cursor.getColumnIndex(KEY_US_SHIRT);
                int id_RJ = cursor.getColumnIndex(KEY_RU_JEANS);
                int id_IJ = cursor.getColumnIndex(KEY_INT_JEANS);
                int id_EJ = cursor.getColumnIndex(KEY_EU_JEANS);
                int id_UJ = cursor.getColumnIndex(KEY_US_JEANS);
                int id_H = cursor.getColumnIndex(KEY_HEIGHT);
                int id_S = cursor.getColumnIndex(KEY_SEX);

                History history = new History(cursor.getString(id_RS),cursor.getString(id_IS),cursor.getString(id_ES),cursor.getString(id_US),cursor.getString(id_RJ),cursor.getString(id_IJ),cursor.getString(id_EJ),cursor.getString(id_UJ),cursor.getString(id_H),cursor.getString(id_S));

                list.add(history);

            }while (cursor.moveToNext());

        db.close();
        return list;
    }


}
