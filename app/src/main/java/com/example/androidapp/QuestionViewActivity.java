package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class QuestionViewActivity extends AppCompatActivity {
    ArrayList<Questions> questions;
    Questions q;
    DBHelperDataMain DBMain;
    DBHelperPollsUsers DBPollsUsers;
    String pollId;
    TextView textViewQuestion;
    TextView textViewQuestionCount;
    TextView textViewCountDown;
    RadioGroup rbGroup;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;
    Button buttonConfirmNext;
    Integer questionCounter;
    Integer questionCountTotal;
    Questions currentQuestion;
    Boolean answered;
    CountDownTimer countDownTimer;
    Long timeLeftInMillis;
    Long timeForPoll;
    String timeCompleted;
    Date currentTime;
    String finishPollTime;
    String pollsQuestionId;
    Boolean pollFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_view);
        questions = new ArrayList<Questions>();
        DBMain = new DBHelperDataMain(this);
        DBPollsUsers = new DBHelperPollsUsers(this);
        questionCountTotal = 0;
        questionCounter = 0;
        answered = false;
        countDownTimer = null;
        timeLeftInMillis = (long) 30000;
        timeForPoll = (long) 0;
        timeCompleted = "";
        currentQuestion = new Questions("", "", "", "", "", "", "");
        q = new Questions("", "", "", "", "", "", "");
        textViewQuestion = findViewById(R.id.text_view_question);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);
        pollFinished = false;

        Intent i = getIntent();
        pollId = (String) i.getSerializableExtra("pollId");

        timeForPoll = GetPollTimer() * 1000;
        timeLeftInMillis = timeForPoll;

        String userName = (String) i.getSerializableExtra("userName");


        storeDataInArraysQuestions();

        questionCountTotal = questions.size();
        startCountDown();

        showNextQuestion();


        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rb1.isChecked()) {
                    Toast.makeText(QuestionViewActivity.this, "Answer Confirmed!", Toast.LENGTH_SHORT).show();
//                    LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
////                    if (ActivityCompat.checkSelfPermission(QuestionViewActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(QuestionViewActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
////                        // TODO: Consider calling
////                        //    ActivityCompat#requestPermissions
////                        // here to request the missing permissions, and then overriding
////                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
////                        //                                          int[] grantResults)
////                        // to handle the case where the user grants the permission. See the documentation
////                        // for ActivityCompat#requestPermissions for more details.
////                        return;
////                    }
//                    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                    double longitude =  location.getLongitude();
//                    double latitude = location.getLatitude();
//                    String longitudeString = String.valueOf(longitude);
//                    String latitudeString = String.valueOf(latitude);
                    Date currentTime = Calendar.getInstance().getTime();
                    finishPollTime = currentTime.toString();
                    UUID idGUID = java.util.UUID.randomUUID();
                    DBPollsUsers.insertDataForPollsQuestion(idGUID.toString(),userName,currentQuestion.getName(),rb1.getText().toString(),finishPollTime,pollId,"Skopje","Skopje");
                    answered = true;
                } else if(rb2.isChecked()) {
                    Toast.makeText(QuestionViewActivity.this, "Answer Confirmed!", Toast.LENGTH_SHORT).show();
                    answered = true;
//                    LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                    if (ActivityCompat.checkSelfPermission(QuestionViewActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(QuestionViewActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                        // TODO: Consider calling
//                        //    ActivityCompat#requestPermissions
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for ActivityCompat#requestPermissions for more details.
//                        return;
//                    }
//                    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                    double longitude =  location.getLongitude();
//                    double latitude = location.getLatitude();
//                    String longitudeString = String.valueOf(longitude);
//                    String latitudeString = String.valueOf(latitude);
                    Date currentTime = Calendar.getInstance().getTime();
                    finishPollTime = currentTime.toString();
                    UUID idGUID = java.util.UUID.randomUUID();

                    DBPollsUsers.insertDataForPollsQuestion(idGUID.toString(),userName,currentQuestion.getName(),rb1.getText().toString(),finishPollTime,pollId,"Skopje","Skopje");

                }else if(rb3.isChecked()) {
                    Toast.makeText(QuestionViewActivity.this, "Answer Confirmed!", Toast.LENGTH_SHORT).show();
                    answered = true;
//                    LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                    if (ActivityCompat.checkSelfPermission(QuestionViewActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(QuestionViewActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                        // TODO: Consider calling
//                        //    ActivityCompat#requestPermissions
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for ActivityCompat#requestPermissions for more details.
//                        return;
//                    }
//                    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                    double longitude =  location.getLongitude();
//                    double latitude = location.getLatitude();
//                    String longitudeString = String.valueOf(longitude);
//                    String latitudeString = String.valueOf(latitude);
                    Date currentTime = Calendar.getInstance().getTime();
                    finishPollTime = currentTime.toString();
                    UUID idGUID = java.util.UUID.randomUUID();

                    DBPollsUsers.insertDataForPollsQuestion(idGUID.toString(),userName,currentQuestion.getName(),rb1.getText().toString(),finishPollTime,pollId,"Skopje","Skopje");

                }else if(rb4.isChecked()) {
                    Toast.makeText(QuestionViewActivity.this, "Answer Confirmed!", Toast.LENGTH_SHORT).show();
                    answered = true;
//                    LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                    if (ActivityCompat.checkSelfPermission(QuestionViewActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(QuestionViewActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                        // TODO: Consider calling
//                        //    ActivityCompat#requestPermissions
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for ActivityCompat#requestPermissions for more details.
//                        return;
//                    }
//                    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                    double longitude =  location.getLongitude();
//                    double latitude = location.getLatitude();
//                    String longitudeString = String.valueOf(longitude);
//                    String latitudeString = String.valueOf(latitude);
                    Date currentTime = Calendar.getInstance().getTime();
                    finishPollTime = currentTime.toString();
                    UUID idGUID = java.util.UUID.randomUUID();

                    DBPollsUsers.insertDataForPollsQuestion(idGUID.toString(),userName,currentQuestion.getName(),rb1.getText().toString(),finishPollTime,pollId,"Skopje","Skopje");

                }

                if(answered) {
                    showNextQuestion();
                }
            }
        });
    }

    void storeDataInArraysQuestions() {
        Cursor cursor = DBMain.readAllDataFromQuestionsByID(pollId);

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

    long GetPollTimer() {
        Cursor cursor = DBMain.readAllDataFromPollsById(pollId);
        Long timer = (long) 0;

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                timer = (long) cursor.getInt(2);
            }
        }
        return  timer;
    }

    private void showNextQuestion() {
        rbGroup.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questions.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getName());
            rb1.setText(currentQuestion.getAnswer1());
            rb2.setText(currentQuestion.getAnswer2());
            rb3.setText(currentQuestion.getAnswer3());
            rb4.setText(currentQuestion.getAnswer4());

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText("Confirm");
        } else {
            finishQuiz();
        }
    }

    private void finishQuiz() {
        Date currentTime = Calendar.getInstance().getTime();
        finishPollTime = currentTime.toString();
        finish();
    }

    private void startCountDown() {
        CountDownTimer countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = (long) 0;
                updateCountDownText();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timeCompleted = timeFormatted;

        textViewCountDown.setText(timeFormatted);
    }
}