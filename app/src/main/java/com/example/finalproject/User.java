package com.example.finalproject;

import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class User implements Parcelable {

   private String username;
    private Workout currentWorkout = new Workout();

    protected User(Parcel in) {
        username = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public ArrayList<Workout> getWorkouts() {
        return workouts;
    }

    public Workout getCurrentWorkout() {
        return currentWorkout;
    }

    private ArrayList<Workout> workouts = new ArrayList<>();

    public void setCurrentWorkout(Workout currentWorkout) {
        this.currentWorkout = currentWorkout;
    }



    public String getUsername() {
        return username;
    }



    public User(String username) {
        this.username = username;

    }

    public void addWorkout(ArrayList<Exerise> exerises, Date date, int timeTaken){

            Workout workout = new Workout(date,timeTaken,exerises);
            workouts.add(workout);

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
    }
}
