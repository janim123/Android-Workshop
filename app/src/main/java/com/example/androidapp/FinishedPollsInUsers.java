package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class FinishedPollsInUsers extends AppCompatActivity {

    DBHelperPollsUsers DBPollsUsers;
    ArrayList<String> pollId;
    ArrayList<String> userName;
    PollsAdapter pollsAdapter;
    RecyclerView recyclerView;
    String username;
    AnswerAdapter answerAdapter;
    ArrayList<String> pollName;
    DBHelperDataMain DBMain;
    ArrayList<String> pollNamePOM;
    Boolean flag;
    ArrayList<String> poll_id, poll_name;
    ArrayList<Integer> poll_time, poll_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_polls_in_users);
        poll_id = new ArrayList<>();
        poll_name = new ArrayList<>();
        poll_time = new ArrayList<>();
        poll_btn = new ArrayList<>();
        recyclerView = new RecyclerView(this);
        DBPollsUsers = new DBHelperPollsUsers(this);
        DBMain = new DBHelperDataMain(this);
        pollId = new ArrayList<String>();
        userName = new ArrayList<String>();
        pollName = new ArrayList<String>();
        pollNamePOM = new ArrayList<String>();
        flag = false;

        Intent i = getIntent();
        username = (String) i.getSerializableExtra("userName");

        Log.d("POll name: ", username);
        storeDataInArrays();
        Log.d("POll ID: ", pollId.get(0));
        for(int s = 0; s < pollId.size(); s++) {
            flag = false;
            for(int k = s+1; k < pollId.size()-1; k++) {
                if(pollId.get(s).equals(pollId.get(k))) {
                    pollId.remove(k);
                    k--;
                }
            }
        }

        for(int j = 0; j < pollName.size()-1; j++) {
            flag = false;
            for(int r = j+1; r < pollName.size(); r++) {
                if(pollName.get(j).equals(pollName.get(r))) {
                    pollName.remove(r);
                    r--;
                }
            }
        }

        for(int m = 0; m < pollName.size(); m++) {
            Log.d("POLLLLLL ", pollName.get(m));
        }
        storeDataInArraysPollName();
        storeDataInArraysPollTimeAndButton();
        recyclerView = findViewById(R.id.recyclerView);
        answerAdapter = new AnswerAdapter(FinishedPollsInUsers.this, pollId, pollName, poll_time, poll_btn, username);
        recyclerView.setAdapter(answerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(FinishedPollsInUsers.this));
    }

    void storeDataInArrays() {
        Cursor cursor = DBPollsUsers.readAllDataByUserName(username);

        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()) {
                pollId.add(cursor.getString(5));
            }
        }
    }
    void storeDataInArraysPollTimeAndButton() {
        for(int i = 0; i < pollId.size(); i++) {
            Cursor cursor = DBMain.readAllDataFromPollsById(pollId.get(i));

            if(cursor.getCount() == 0) {
                Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
            } else {
                while(cursor.moveToNext()) {
                    poll_time.add(cursor.getInt(2));
                    poll_btn.add(cursor.getInt(3));
                }
            }
        }
    }
    void storeDataInArraysPollName() {
        for(int i = 0; i < pollId.size(); i++) {
            Cursor cursor = DBMain.readPollNameFromPollsByIdDistinct(pollId.get(i));

            if(cursor.getCount() == 0) {
                Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
            } else {
                while(cursor.moveToNext()) {
                    pollName.add(cursor.getString(0));
                }
            }
        }
    }
}