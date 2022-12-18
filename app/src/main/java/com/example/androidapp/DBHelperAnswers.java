package com.example.androidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperAnswers extends SQLiteOpenHelper {

    public static final String DBName = "Login.db";

    public DBHelperAnswers(Context context) {
        super(context, "DataMain.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("Create Table polls(id TEXT primary key, name TEXT, time INTEGER, btn INTEGER)");
        MyDB.execSQL("Create Table questions(id TEXT primary key, name TEXT, answer1 TEXT, answer2 TEXT, answer3 TEXT, answer4 TEXT, pollId TEXT, foreign key (pollId) references polls(id) on delete cascade)");
        MyDB.execSQL("Create Table polls_question(id TEXT primary key,nameUser TEXT,nameQuestion TEXT, answer TEXT,date TEXT, pollId TEXT,longitude TEXT, latitude TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists polls");
        MyDB.execSQL("drop Table if exists questions");
        MyDB.execSQL("drop Table if exists polls_question");
        onCreate(MyDB);
    }

    public Boolean insertDataForPollsQuestion(String id, String nameUser, String nameQuestion, String answer, String date, String longitude,String latitude) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("nameUser", nameUser);
        contentValues.put("nameQuestion", nameQuestion);
        contentValues.put("answer", answer);
        contentValues.put("date", date);
        contentValues.put("longitude", longitude);
        contentValues.put("latitude", latitude);

        long result = MyDB.insert("polls_question", null, contentValues);
        if(result == -1) return false;
        else return true;
    }
    public Boolean insertDataForPolls(String id, String name, Integer time, Integer btn) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("time", time);
        contentValues.put("btn", btn);
        long result = MyDB.insert("polls", null, contentValues);
        if(result == -1) return false;
        else return true;
    }

    public Boolean insertDataForQuestions(String id, String name, String answer1, String answer2, String answer3, String answer4, String pollId) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("answer1", answer1);
        contentValues.put("answer2", answer2);
        contentValues.put("answer3", answer3);
        contentValues.put("answer4", answer4);
        contentValues.put("pollId", pollId);
        long result = MyDB.insert("questions", null, contentValues);
        if(result == -1) return false;
        else return true;
    }

    Cursor readAllDataFromPolls(){
        String query = "SELECT * FROM polls";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllDataFromPollsById(String id){
        String query = "SELECT * FROM polls";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("SELECT * FROM " + "polls" + " where id = '" + id + "'" , null);
        }
        return cursor;
    }

    Cursor readAllDataFromQuestions(){
        String query = "SELECT * FROM questions";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllDataFromQuestionsByID(String id){
        String query = "SELECT * FROM questions WHERE pollId=id";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("SELECT * FROM " + "questions" + " where pollId = '" + id + "'" , null);
        }
        return cursor;
    }

}