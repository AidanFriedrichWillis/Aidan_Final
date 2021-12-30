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

    public void setDate(Date date) {

        this.date = date;
    }

    public void setTimetaken(int timetaken) {
        this.timetaken = timetaken;
    }

    public void setExerises(ArrayList<Exerise> exerises) {
        this.exerises = exerises;
    }

    private Date date;
    private int timetaken;
    private ArrayList<Exerise> exerises;

    public Workout(Date date, int timetaken, ArrayList<Exerise> exerises) {
        this.date = date;
        this.timetaken = timetaken;
        this.exerises = exerises;
    }
    public Workout(ArrayList<Exerise> exerises) {
        this.date = java.util.Calendar.getInstance().getTime();
        this.timetaken = 0;
        this.exerises = exerises;

    }
    public Workout() {
        this.date = java.util.Calendar.getInstance().getTime();
        this.timetaken = 0;
        ArrayList<Exerise> exerises = new ArrayList<Exerise>();
        this.exerises = exerises;

    }


    public void  addExersize (Exerise exerise){
        exerises.add(exerise);

    }



}
