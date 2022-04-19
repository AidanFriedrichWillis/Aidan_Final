package com.example.finalproject.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * The User class, has less attributes then the model in the server, less sensitive data.
 * Holds the temp workout data we need when running the app.
 */
public final class User {
    private static Workout currentWorkout = new Workout();
    private static ArrayList<Workout> workouts = new ArrayList<>();
    /**
     *
     * @return Workouts from the user
     */
    public static ArrayList<Workout> getWorkouts() {
        return workouts;
    }
    /**
     *
     * @return Current workout from the user.
     */
    public static Workout getCurrentWorkout() {
        return currentWorkout;
    }
    /**
     * Sets the workouts to the workouts received from server.
     * @param workouts List of workouts
     */
    public static void setWorkouts(ArrayList<Workout> workouts) { User.workouts = workouts; }
    public User() {}
    /**
     * Removes any data the user may have in the scope of the application
     * @return True if the data is reset.
     */
    public  static boolean resetUser(){
        currentWorkout = null;
        if(currentWorkout == null){
            return true;
        }
        return false;
    }
    /**
     * Gets a list of all the names from the workouts we have in memory.
     * @return A list of Strings containing the names of the exercises.
     */
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
    /**
     * Creates a list of exercises based on the JSON object we revived from the server.
     * @param jsonST The JSon in string format
     * @return A list of workout objects, which we can use.
     * @throws JSONException
     */
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
