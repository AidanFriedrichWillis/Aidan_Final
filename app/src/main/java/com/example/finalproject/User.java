package com.example.finalproject;

import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;

public final class User {

    private static String username;
    private static Workout currentWorkout = new Workout();

    public static void setWorkouts(ArrayList<Workout> workouts) {
        User.workouts = workouts;
    }

    private static ArrayList<Workout> workouts = new ArrayList<>();


    public static ArrayList<Workout> getWorkouts() {
        return workouts;
    }

    public static Workout getCurrentWorkout() {
        return currentWorkout;
    }


    public static void setCurrentWorkout(Workout cw) {
        currentWorkout = cw;
    }



    public static String getUsername() {
        return username;
    }



    private User(String u) {
        username = username;
    }

    public User(String username, ArrayList<Workout> workouts){
        this.username = username;
        this.workouts = workouts;


    }


    public static void setUsername(String u){
        username = u;

    }

    public static void addWorkout(ArrayList<Exerise> exerises, Date date, String n){

            Workout workout = new Workout(date,exerises,n);
            workouts.add(workout);

    }

    public static User returnUSer(){

        return new User(User.getUsername(),User.getWorkouts());

    }

}
