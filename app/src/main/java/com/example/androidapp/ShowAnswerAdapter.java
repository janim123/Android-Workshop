package com.example.androidapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowAnswerAdapter extends RecyclerView.Adapter<ShowAnswerAdapter.MyViewHolder> {

    Context context;
    ArrayList poll_time,questions,answers,users;
    String userName;
    Boolean hasUserAnswered = false;
    DBHelperPollsUsers DBPollsUsers;
    Boolean adminOrUser;


    ShowAnswerAdapter(Context context, ArrayList poll_time, ArrayList questions, ArrayList answers, String userName,Boolean adminOrUser,ArrayList users) {
        this.context = context;
        this.questions = questions;
        this.answers = answers;
        this.poll_time = poll_time;
        this.userName = userName;
        this.adminOrUser = adminOrUser;
        this.users = users;
    }

    @NonNull
    @Override
    public ShowAnswerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.myrow_answers, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowAnswerAdapter.MyViewHolder holder, int position) {

        holder.questions_name_txt.setText(String.valueOf(questions.get(position)));
        if(!adminOrUser){
            holder.user_txt.setText(userName);
        }
        else if(adminOrUser){
            holder.user_txt.setText(String.valueOf(users.get(position)));
        }
        holder.answers_txt.setText(String.valueOf(answers.get(position)));
        holder.time_txt.setText(String.valueOf(poll_time.get(position)));

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView questions_name_txt, answers_txt, time_txt, user_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            questions_name_txt = itemView.findViewById(R.id.question_name);
            answers_txt = itemView.findViewById(R.id.answer);
            time_txt = itemView.findViewById(R.id.poll_time);
            user_txt = itemView.findViewById(R.id.user);
        }
    }

}