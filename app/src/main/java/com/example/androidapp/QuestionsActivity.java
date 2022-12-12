package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {
    EditText addQuestion, answer1, answer2, answer3, answer4;
    Questions questions;
    Button anotherQuestion;
    Button finishPoll;
    Integer count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        addQuestion = (EditText)findViewById(R.id.inputQuestion);
        answer1 = (EditText)findViewById(R.id.inputAnswer1);
        answer2 = (EditText)findViewById(R.id.inputAnswer2);
        answer3 = (EditText)findViewById(R.id.inputAnswer3);
        answer4 = (EditText)findViewById(R.id.inputAnswer4);
        anotherQuestion = (Button) findViewById(R.id.buttonAddQuestion);
        finishPoll = (Button) findViewById(R.id.buttonFinishPoll);
        questions = new Questions();
        questions.name = "";
        questions.answers = new ArrayList<>();

        Intent i = getIntent();
        Poll poll = (Poll) i.getSerializableExtra("poll");
        poll.questions = new ArrayList<>();

        anotherQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = addQuestion.getText().toString();
                String an1 = answer1.getText().toString();
                String an2 = answer2.getText().toString();
                String an3 = answer3.getText().toString();
                String an4 = answer4.getText().toString();

                questions.name = name;
                questions.answers.add(an1);
                questions.answers.add(an2);
                questions.answers.add(an3);
                questions.answers.add(an4);

                poll.questions.add(questions);

                    count++;

                Intent intent = new Intent(getApplicationContext(), QuestionsActivity.class);
                intent.putExtra("poll", poll);
                startActivity(intent);
            }
        });

        finishPoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                intent.putExtra("poll", poll);
                Log.d("myTag", poll.name);
                startActivity(intent);

            }
        });

    }
}