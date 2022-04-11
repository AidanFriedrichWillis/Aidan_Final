package com.example.finalproject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Workout {

    public String getDate() {
        return date;
    }

    public ArrayList<Exerise> getExerises() {
        return exerises;
    }

    public void setDate(Date date) {

        this.date = date.toString();
    }

    public String getName() {
        return name;
    }

    private String name = "";
    public void setExerises(ArrayList<Exerise> exerises) {
        this.exerises = exerises;
    }


    private String date;
    private ArrayList<Exerise> exerises = new ArrayList<>();


    public void setName(String n){
        name = n;
    }

    public Workout(String date, ArrayList<Exerise> exerises, String n) {
       this.name = n;
        this.date = date.toString();
        this.exerises = exerises;
    }
    public Workout(String n, String date) {
        this.name = n;
        this.date = date.toString();
        this.exerises = exerises;
    }

    public Workout(ArrayList<Exerise> exerises) {
        this.date = java.util.Calendar.getInstance().getTime().toString();
        this.exerises = exerises;

    }
    public Workout() {
        this.date = java.util.Calendar.getInstance().getTime().toString();
        ArrayList<Exerise> exerises = new ArrayList<Exerise>();
        this.exerises = exerises;

    }
    public Workout(String n) {
        name = n;
        this.date = java.util.Calendar.getInstance().getTime().toString();
        ArrayList<Exerise> exerises = new ArrayList<Exerise>();
        this.exerises = exerises;

    }

    public void  addExersize (Exerise exerise){
        exerises.add(exerise);

    }





}
