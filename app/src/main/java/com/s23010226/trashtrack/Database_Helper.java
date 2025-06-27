package com.s23010226.trashtrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database_Helper extends SQLiteOpenHelper {

    private static final String DATABASE = "users.db";
    private static final String TABLE = "users_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "USERNAME";
    private static final String COL_3 = "EMAIL";
    private static final String COL_4 = "PHONE_NUMBER";
    private static final String COL_5 = "PASSWORD";



    public Database_Helper(@Nullable Context context) {
        super(context, DATABASE, null, 1);
        SQLiteDatabase db = this.getReadableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, EMAIL TEXT, PHONE_NUMBER TEXT, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public boolean insertUser(String username, String email, String phone, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, username);
        cv.put(COL_3, email);
        cv.put(COL_4, phone);
        cv.put(COL_5, password);
        long result = db.insert(TABLE, null, cv);
        return result != -1;
    }

    public boolean validateUser(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE + "WHERE " + COL_3 + "=? AND " + COL_5 + "=?", new String[]{email, password}
        );

        boolean valid = (cursor.getCount() > 0);
        cursor.close();
        return valid;
    }
}
