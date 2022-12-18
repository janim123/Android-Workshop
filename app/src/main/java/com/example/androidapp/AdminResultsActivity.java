package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminResultsActivity extends AppCompatActivity {

    DBHelperPollsUsers DBPollsUsers;
    String pollId;
    RecyclerView recyclerView;
    String username;
    ShowAnswerAdapter showAnswerAdapter;
    ArrayList<String> questions, answers,time,users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_answers_user);
        recyclerView = new RecyclerView(this);
        DBPollsUsers = new DBHelperPollsUsers(this);
        questions = new ArrayList<String>();
        answers = new ArrayList<String>();
        time = new ArrayList<String>();
        users = new ArrayList<String>();


        Intent i = getIntent();
        username = (String) i.getSerializableExtra("userName");
        pollId = (String) i.getSerializableExtra("pollId") ;

        storeDataInArrays();
        recyclerView = findViewById(R.id.recyclerView);

        showAnswerAdapter = new ShowAnswerAdapter(AdminResultsActivity.this, time,questions,answers,username,true,users);
        recyclerView.setAdapter(showAnswerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminResultsActivity.this));

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShowAnswersUser.class);
                startActivity(intent);
            }
        });
    }

    void storeDataInArrays() {
        Cursor cursor = DBPollsUsers.readAllDataFromPollsByIdResults(pollId);

        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()) {
                users.add(cursor.getString(1));
                questions.add(cursor.getString(2));
                answers.add(cursor.getString(3));
                time.add(cursor.getString(4));
            }
        }
    }
}