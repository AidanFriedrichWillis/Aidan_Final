package com.example.finalproject.Models;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Workout {
    private String id;

    public String getDate() {

        return new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(Long.parseLong(this.date));
    }

    public ArrayList<Exerise> getExerises() {
        return exerises;
    }


    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    private String name = "";

    private String date;
    private ArrayList<Exerise> exerises = new ArrayList<>();


    public void setName(String n) {
        name = n;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Workout(String n, String date, String id) {
        this.name = n;
        this.date = date;
        this.id = id;
    }

    public Workout() {
        this.date = java.util.Calendar.getInstance().getTime().toString();
        ArrayList<Exerise> exerises = new ArrayList<Exerise>();
        this.exerises = exerises;

    }

    public void addExersize(Exerise exerise) {
        exerises.add(exerise);

    }


}
