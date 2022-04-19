package com.example.finalproject.Models;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Workout {
    private String id;
    private String name = "";
    private String date;
    /**
     * Creates a workout with params:
     * @param n String name
     * @param date String Date
     * @param id String ID
     */

    public Workout(String n, String date, String id) {
        this.name = n;
        this.date = date;
        this.id = id;
    }
    /**
     * Creates a workout with Params
     */
    public Workout() {
        this.date = java.util.Calendar.getInstance().getTime().toString();
        ArrayList<Exerise> exerises = new ArrayList<Exerise>();
        this.exerises = exerises;
    }
    private ArrayList<Exerise> exerises = new ArrayList<>();
    /**
     *
     * @return date as a string
     */
    @SuppressLint("SimpleDateFormat")
    public String getDate() { return new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(Long.parseLong(this.date)); }
    /**
     *
     * @return Exercises as a list
     */
    public ArrayList<Exerise> getExerises() {
        return exerises;
    }
    /**
     *
     * @return name as a String
     */
    public String getName() {
        return name;
    }
    /**
     *
     * @return Id as a String
     */
    public String getId() {
        return id;
    }
    /**
     *
     * @param n Sets the name as N
     */
    public void setName(String n) {
        name = n;
    }
    /**
     *
     * @param date Sets date as Date
     */
    public void setDate(String date) {
        this.date = date;
    }
    /**
     *
     * @param exerise Adds an exercise to a list.
     */
    public void addExersize(Exerise exerise) { exerises.add(exerise); }


}
