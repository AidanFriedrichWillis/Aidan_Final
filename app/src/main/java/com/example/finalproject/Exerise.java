package com.example.finalproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Exerise implements Parcelable {

    protected Exerise(Parcel in) {
        name = in.readString();
        reps = in.readInt();
        sets = in.readInt();
    }

    public static final Creator<Exerise> CREATOR = new Creator<Exerise>() {
        @Override
        public Exerise createFromParcel(Parcel in) {
            return new Exerise(in);
        }

        @Override
        public Exerise[] newArray(int size) {
            return new Exerise[size];
        }
    };

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

    public Exerise(String name, int reps, int sets) {
        this.name = name;
        this.reps = reps;
        this.sets = sets;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(reps);
        dest.writeInt(sets);
    }
}
