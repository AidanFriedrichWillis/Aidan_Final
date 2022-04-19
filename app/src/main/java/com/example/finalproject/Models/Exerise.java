package com.example.finalproject.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * An exercise Model based upon data from the model we have on the server, keeping everything where it should be.
 */
public class Exerise {
    private final String name;
    private final int reps;
    private final int sets;
    private final int weight;
    /**
     *
     * @return Name as String
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Reps as Int
     */
    public int getReps() {
        return reps;
    }

    /**
     *
     * @return Sets as Int
     */
    public int getSets() {
        return sets;
    }

    /**
     *
     * @return Weight as Int
     */
    public int getWeight() {
        return weight;
    }
    /**
     * Creates the exercise object.
     * @param name Name of Exercise
     * @param reps Reps of Exercise
     * @param sets Sets of Exercise
     * @param weight Weight of Exercise
     */
    public Exerise(String name, int reps, int sets,int weight) {
        this.name = name;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
    }

}
