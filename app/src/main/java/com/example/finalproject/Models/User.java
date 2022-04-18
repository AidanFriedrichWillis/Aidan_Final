package com.example.finalproject.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public final class User {

    private static Workout currentWorkout = new Workout();
    private static ArrayList<Workout> workouts = new ArrayList<>();

    public static ArrayList<Workout> getWorkouts() {
        return workouts;
    }
    public static Workout getCurrentWorkout() {
        return currentWorkout;
    }
    public static void setWorkouts(ArrayList<Workout> workouts) { User.workouts = workouts; }
    public  static boolean resetUser(){
        currentWorkout = null;
        if(currentWorkout == null){
            return true;
        }
        return false;
    }

    public User() {}

    public static ArrayList<String> allENames() {

        ArrayList<String> enames = new ArrayList<>();

        for (int i = 0; i < workouts.size(); i++) {

            for (int j = 0; j < workouts.get(i).getExerises().size(); j++) {

                if (!enames.contains(workouts.get(i).getExerises().get(j).getName())) {
                    enames.add(workouts.get(i).getExerises().get(j).getName());
                }
            }
        }
        return enames;
    }

    public static ArrayList<Workout> fromJson(String jsonST) throws JSONException {
        ArrayList<Workout> workouts = new ArrayList<>();

        if (jsonST != "") {

            JSONArray jsonArray = new JSONArray(jsonST);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject tempo = new JSONObject(jsonArray.get(i).toString());

                Workout w = new Workout(tempo.get("name").toString(), tempo.get("date").toString(), tempo.get("_id").toString());
                JSONArray tempa = new JSONArray(tempo.getJSONArray("exersizes").toString());

                for (int j = 0; j < tempa.length(); j++) {

                    String name;
                    int reps;
                    int sets;
                    int weight;
                    JSONObject tempo2 = new JSONObject(tempa.get(j).toString());

                    name = (String) tempo2.get("name");
                    reps = (int) tempo2.get("reps");
                    sets = (int) tempo2.get("sets");
                    weight = (int) tempo2.get("weight");
                    Exerise e = new Exerise(name, reps, sets, weight);
                    w.addExersize(e);

                }

                workouts.add(w);


            }

        }

        User.setWorkouts(workouts);
        return workouts;

    }


}
