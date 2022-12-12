package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class PollActivity extends AppCompatActivity {
    EditText namePoll;
    Poll poll;
    Button btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);

        namePoll = (EditText) findViewById(R.id.inputPollName);
        btn = (Button) findViewById(R.id.buttonAddQuestion2);
        poll = new Poll();
        poll.id = java.util.UUID.randomUUID();
        poll.questions = null;
        poll.name = "";

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pollname = namePoll.getText().toString();
                poll.name = pollname;
                Intent intent = new Intent(getApplicationContext(), QuestionsActivity.class);
                intent.putExtra("poll", poll);
                startActivity(intent);
            }
        });
    }
}