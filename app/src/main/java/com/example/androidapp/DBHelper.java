package com.example.androidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "Login.db";


    public static String DATABASE_NAME = "polls";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_POLLS = "polls";
    private static final String TABLE_USER_HOBBY = "users_hobby";
    private static final String TABLE_USER_CITY = "users_city";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "name";
    private static final String KEY_TIME = "time";
    private static final String KEY_CITY = "city";
    private static final String CREATE_TABLE_POLLS = "CREATE TABLE "
            + TABLE_POLLS + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FIRSTNAME + " TEXT ," + KEY_TIME + " TEXT );";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
        MyDB.execSQL(CREATE_TABLE_POLLS);
        MyDB.execSQL("create Table polls(id TEXT primary key, name TEXT, time INTEGER)");
        MyDB.execSQL("create Table questions(id TEXT primary key, name TEXT, answer1 TEXT,answer2 TEXT,answer3 TEXT,answer4 TEXT,pollId TEXT,foreign key (pollId) references polls(id) on delete cascade )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists polls");
        //MyDB.execSQL("drop Table if exists questions");
        onCreate(MyDB);
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if(result == -1) return false;
        else return true;
    }

    public Boolean insertDataForPolls(String id,String name, Integer time) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("time", time);
        long result = MyDB.insert("polls", null, contentValues);
        if(result == -1) return false;
        else return true;
    }
    public Boolean insertDataForQuestions(String id,String name, String answer1,String answer2,String answer3,String answer4,String pollId) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("name",name);
        contentValues.put("answer1", answer1);
        contentValues.put("answer2", answer2);
        contentValues.put("answer3", answer3);
        contentValues.put("answer4", answer4);
        contentValues.put("pollId", pollId);

        long result = MyDB.insert("questions", null, contentValues);
        if(result == -1) return false;
        else return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[] {username});

        if(cursor.getCount() > 0) return true;
        else return false;
    }

    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username, password});

        if(cursor.getCount() > 0) return true;
        else return false;
    }

    public Boolean updateData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        String where = "username=?";
        String[] whereArgs = new String[] {String.valueOf(username)};
        long result = MyDB.update("users", contentValues, where, whereArgs);
        if(result == -1) return false;
        else return true;
    }
}
