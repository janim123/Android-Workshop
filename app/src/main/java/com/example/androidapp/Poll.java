package com.example.androidapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Poll implements Serializable {
    UUID id;
    String name;
    Integer time;
    ArrayList<Questions> questions = new ArrayList<Questions>();
}
