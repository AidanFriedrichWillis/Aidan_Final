package com.example.finalproject;

import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public final class User {

    private static String username;
    private static Workout currentWorkout = new Workout();
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
    public static void setUsername(String u){
        username = u;

    }

    public static void addWorkout(ArrayList<Exerise> exerises, Date date){

            Workout workout = new Workout(date,exerises);
            workouts.add(workout);

    }

}
