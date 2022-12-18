package com.example.androidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperData extends SQLiteOpenHelper {

    public static final String DBName = "Login.db";

    public DBHelperData(Context context) {
        super(context, "Pomosna.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB2) {
        MyDB2.execSQL("create Table users(username TEXT primary key, password TEXT)");
        MyDB2.execSQL("Create Table polls(id TEXT primary key, name TEXT, time INTEGER)");
        MyDB2.execSQL("Create Table questions(id TEXT primary key, name TEXT, answer1 TEXT, answer2 TEXT, answer3 TEXT, answer4 TEXT, pollId TEXT, foreign key (pollId) references polls(id) on delete cascade)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB2, int i, int i1) {
        MyDB2.execSQL("drop Table if exists users");
        MyDB2.execSQL("drop Table if exists polls");
        MyDB2.execSQL("drop Table if exists questions");
        onCreate(MyDB2);
    }


    public Boolean insertDataForPolls(String id, String name, Integer time) {
        SQLiteDatabase MyDB2 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("time", time);
        long result = MyDB2.insert("polls", null, contentValues);
        if(result == -1) return false;
        else return true;
    }

    public Boolean insertDataForQuestions(String id, String name, String answer1, String answer2, String answer3, String answer4, String pollId) {
        SQLiteDatabase MyDB2 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("answer1", answer1);
        contentValues.put("answer2", answer2);
        contentValues.put("answer3", answer3);
        contentValues.put("answer4", answer4);
        contentValues.put("pollId", pollId);
        long result = MyDB2.insert("questions", null, contentValues);
        if(result == -1) return false;
        else return true;
    }

}
