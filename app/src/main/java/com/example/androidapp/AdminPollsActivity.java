package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminPollsActivity extends AppCompatActivity {

    DBHelperDataMain DBMain;
    DBHelperAnswers DBAnswers;
    Button Results;
    RecyclerView recyclerView;
    ArrayList<String> poll_id, poll_name;
    ArrayList<Integer> poll_time, poll_btn;
    PollsAdapter pollsAdapter;
    ArrayList<Questions> questions;
    Questions q;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_polls);

        DBMain = new DBHelperDataMain(this);
        DBAnswers = new DBHelperAnswers(this);

        poll_id = new ArrayList<>();
        poll_name = new ArrayList<>();
        poll_time = new ArrayList<>();
        poll_btn = new ArrayList<>();
        recyclerView = new RecyclerView(this);
        TextView textView = (TextView) findViewById(R.id.textView);
        questions = new ArrayList<Questions>();
        q = new Questions("", "", "", "", "", "", "");

        recyclerView = findViewById(R.id.recyclerView);

        Intent i = getIntent();
        userName = (String) i.getSerializableExtra("username");


        storeDataInArrays();

        pollsAdapter = new PollsAdapter(AdminPollsActivity.this, poll_id, poll_name, poll_time, poll_btn, userName,true);
        recyclerView.setAdapter(pollsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminPollsActivity.this));

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShowAnswersUser.class);
                startActivity(intent);
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        storeDataInArraysQuestions();

    }

    void storeDataInArrays() {
        Cursor cursor = DBMain.readAllDataFromPolls();

        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()) {
                poll_id.add(cursor.getString(0));
                poll_name.add(cursor.getString(1));
                poll_time.add(cursor.getInt(2));
                poll_btn.add(cursor.getInt(3));
            }
        }
    }

    void storeDataInArraysQuestions() {
        Cursor cursor = DBMain.readAllDataFromQuestions();

        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()) {
                q = new Questions("", "", "", "", "", "", "");
                q.setId(cursor.getString(0));
                q.setName(cursor.getString(1));
                q.setAnswer1(cursor.getString(2));
                q.setAnswer2(cursor.getString(3));
                q.setAnswer3(cursor.getString(4));
                q.setAnswer4(cursor.getString(5));
                q.setPollId(cursor.getString(6));
                questions.add(q);
            }
        }
    }
}