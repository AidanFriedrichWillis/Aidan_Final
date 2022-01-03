package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ExeriszeInfo extends AppCompatActivity {

    private TextView exersizeInfoTV;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exerisze_info);
        Intent intent = getIntent();

        int index = intent.getIntExtra("index",0);



        exersizeInfoTV = findViewById(R.id.exersizeInfoTV);

        String s = "Workout Name: "+ User.getWorkouts().get(index).getName() + "  Date:" + User.getWorkouts().get(index).getDate().toString() +"\n";

        for(int i = 0; i < User.getWorkouts().get(index).getExerises().size();i++){

            s += User.getWorkouts().get(index).getExerises().get(i).getName() + " reps: " +User.getWorkouts().get(index).getExerises().get(i).getReps()+ " sets: " +User.getWorkouts().get(index).getExerises().get(i).getSets() + "\n";


        }

        exersizeInfoTV.setText(s);

    }
}