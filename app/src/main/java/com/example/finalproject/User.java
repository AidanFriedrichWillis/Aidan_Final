package com.example.finalproject;

import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

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

    public static int pb(String e, int month, int year){
        int pb = 0;

        for(int i = 0; i< workouts.size(); i++){
            Date d = new Date(workouts.get(i).getDate());
            int m = d.getMonth();
            for(int j = 0; j < workouts.get(i).getExerises().size(); j++) {
                Exerise exerise = workouts.get(i).getExerises().get(j);
                if(d.getMonth() == month){
                if(workouts.get(i).getExerises().get(j).getName().equals(e) && workouts.get(i).getExerises().get(j).getWeight() > pb && d.getYear() == year){
                    pb = workouts.get(i).getExerises().get(j).getWeight();
                }


            }
            }


        }


        return pb;
    }

    public static ArrayList<String> allENames(){

        ArrayList<String> enames = new ArrayList<>();

        for(int i = 0; i< workouts.size(); i++){

            for(int j = 0; j < workouts.get(i).getExerises().size(); j++) {

                if(!enames.contains(workouts.get(i).getExerises().get(j).getName())){
                    enames.add(workouts.get(i).getExerises().get(j).getName());
                }

            }


        }

        return enames;

    }

    public static void addWorkout(ArrayList<Exerise> exerises, String date, String n){

            Workout workout = new Workout(date,exerises,n);
            workouts.add(workout);

    }

    public static User returnUSer(){

        return new User(User.getUsername(),User.getWorkouts());

    }

    public static ArrayList<Workout> fromJson(String jsonST) throws JSONException {







        //        JSONParser jsonParser = new JSONParser();
//
//        Object o = jsonParser.parse(jsonST);
//
//        JSONArray workoutList = (JSONArray) o;

       // Log.d("ree", workoutList.toString());






        return workouts;

    }



}
