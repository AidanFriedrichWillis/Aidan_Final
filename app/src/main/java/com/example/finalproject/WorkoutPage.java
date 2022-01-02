package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class WorkoutPage extends AppCompatActivity {

    private Button addExersiseBTN;
    private TextView usernameTV;
    private TextView exersizesTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_page);

        addExersiseBTN = findViewById(R.id.addExersise1);
        usernameTV = findViewById(R.id.usernameTV1);
        usernameTV.setText(User.getUsername());
        exersizesTV = findViewById(R.id.exersizesTV1);
        String s = "";
        exersizesTV.setText(s);
        if(!User.getCurrentWorkout().getExerises().isEmpty()){
            for(int i = 0; i < User.getCurrentWorkout().getExerises().size(); i++){
                s += "Exersize: " +  User.getCurrentWorkout().getExerises().get(i).getName() + " Reps:" + User.getCurrentWorkout().getExerises().get(i).getReps() + " Sets:" + User.getCurrentWorkout().getExerises().get(i).getSets() +"\n";
            }
            exersizesTV.setText(s);
        }





    }


    public void addExerise(View view){

        Intent intent = new Intent(getApplicationContext(),AddExersize.class);
        startActivity(intent);


    }


}