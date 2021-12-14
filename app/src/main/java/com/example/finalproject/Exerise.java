package com.example.finalproject;

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

    public int getSeconds() {
        return seconds;
    }

    private String name;
    private int reps;
    private int sets;
    private int seconds;

    public Exerise(String name, int reps, int sets, int seconds) {
        this.name = name;
        this.reps = reps;
        this.sets = sets;
        this.seconds = seconds;
    }
}
