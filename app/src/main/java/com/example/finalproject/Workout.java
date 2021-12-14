package com.example.finalproject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Workout {

    public Date getDate() {
        return date;
    }

    public int getTimetaken() {
        return timetaken;
    }

    public ArrayList<Exerise> getExerises() {
        return exerises;
    }

    private Date date;
    private int timetaken;
    private ArrayList<Exerise> exerises;

    public Workout(Date date, int timetaken, ArrayList<Exerise> exerises) {
        this.date = date;
        this.timetaken = timetaken;
        this.exerises = exerises;

    }




}
