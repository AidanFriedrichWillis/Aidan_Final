package com.example.finalproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Exerise {



    public String getName() {
        return name;
    }

    public int getReps() {
        return reps;
    }

    public int getSets() {
        return sets;
    }



    private String name;
    private int reps;
    private int sets;

    public int getWeight() {
        return weight;
    }

    private int weight;

    public Exerise(String name, int reps, int sets,int weight) {
        this.name = name;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
    }

}
