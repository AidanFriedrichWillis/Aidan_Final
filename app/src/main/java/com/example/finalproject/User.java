package com.example.finalproject;

import java.util.ArrayList;
import java.util.Date;

public class User {

  private String fullname;
   private String username;
   private String email;
   private ArrayList<Workout> workouts = new ArrayList<>();

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public User(String fullname, String username, String email) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
    }

    public void addWorkout(ArrayList<Exerise> exerises, Date date, int timeTaken){

            Workout workout = new Workout(date,timeTaken,exerises);
            workouts.add(workout);

    }


}
